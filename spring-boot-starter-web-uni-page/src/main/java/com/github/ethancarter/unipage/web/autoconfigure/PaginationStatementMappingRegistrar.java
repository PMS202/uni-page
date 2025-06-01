package com.github.ethancarter.unipage.web.autoconfigure;

import com.github.ethancarter.unipage.web.mvc.PaginationHandlerMethodRegistry;
import com.github.ethancarter.unipage.web.mvc.PaginationUriResolver;
import com.github.ethancarter.unipage.web.mvc.UniPageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringValueResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Slf4j
class PaginationStatementMappingRegistrar
        implements ApplicationListener<ContextRefreshedEvent>, EmbeddedValueResolverAware {

    private final PaginationUriResolver paginationUriResolver;
    private final RequestMappingHandlerMapping handlerMapping;
    private final PaginationHandlerMethodRegistry registry;
    private final UniPageProperties uniPageProperties;
    @Nullable
    private StringValueResolver embeddedValueResolver;


    public PaginationStatementMappingRegistrar(PaginationUriResolver paginationUriResolver,
                                               RequestMappingHandlerMapping handlerMapping,
                                               PaginationHandlerMethodRegistry registry,
                                               UniPageProperties uniPageProperties) {
        this.paginationUriResolver = paginationUriResolver;
        this.handlerMapping = handlerMapping;
        this.registry = registry;
        this.uniPageProperties = uniPageProperties;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (String pageKey : registry.getAllPaginationKey()) {
            HandlerMethod statementMethod = registry.getHandler(pageKey);
            for (UniPageProperties.Action action : uniPageProperties.getActions()) {
                // Check if the pageKey matches the include/exclude conditions
                if (shouldApplyAction(pageKey, action)) {
                    String path = paginationUriResolver.constructUri(pageKey, action.getAction());
                    Method method = statementMethod.getMethod();
                    RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
                    Assert.notNull(requestMapping, "RequestMapping annotation not found on method " + method);
                    RequestMappingInfo.Builder builder = RequestMappingInfo
                            .paths(resolveEmbeddedValuesInPattern(path))
                            .options(handlerMapping.getBuilderConfiguration())
                            .methods(requestMapping.method())
                            .params(requestMapping.params())
                            .headers(requestMapping.headers())
                            .consumes(requestMapping.consumes())
                            .produces(requestMapping.produces())
                            .mappingName(requestMapping.name());
                    handlerMapping.registerMapping(builder.build(), statementMethod.getBean(), statementMethod.getMethod());
                }
            }
        }
    }

    private boolean shouldApplyAction(String pageKey, UniPageProperties.Action action) {
        boolean included = CollectionUtils.isEmpty(action.getIncludePages())
                || action.getIncludePages().contains(pageKey);
        boolean excluded = !CollectionUtils.isEmpty(action.getExcludePages())
                && action.getExcludePages().contains(pageKey);
        return included && !excluded;
    }

    protected String resolveEmbeddedValuesInPattern(String pattern) {
        if (this.embeddedValueResolver == null) {
            return pattern;
        } else {
            return this.embeddedValueResolver.resolveStringValue(pattern);
        }
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.embeddedValueResolver = resolver;
    }
}
