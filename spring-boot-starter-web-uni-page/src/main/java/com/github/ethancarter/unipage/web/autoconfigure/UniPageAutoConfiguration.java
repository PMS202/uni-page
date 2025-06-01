package com.github.ethancarter.unipage.web.autoconfigure;

import com.github.ethancarter.unipage.web.component.PaginationComponentFactory;
import com.github.ethancarter.unipage.executor.PaginationStatementExecutor;
import com.github.ethancarter.unipage.executor.PaginationStatementOperations;
import com.github.ethancarter.unipage.executor.PaginationStatementTemplate;
import com.github.ethancarter.unipage.web.component.DefaultPaginationComponentFactory;
import com.github.ethancarter.unipage.web.mvc.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collection;
import java.util.List;

@AutoConfiguration
@Import(PaginationActionHandlerConfiguration.class)
@EnableConfigurationProperties(UniPageProperties.class)
public class UniPageAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(PaginationStatementOperations.class)
    public PaginationStatementOperations paginationStatementOperations(Collection<PaginationStatementExecutor<?>> executors) {
        PaginationStatementTemplate template = new PaginationStatementTemplate();
        executors.forEach(template::addStatementExecutor);
        return template;
    }

    @Bean
    PaginationStatementMappingRegistrar statementMappingRegistrar(PaginationUriResolver paginationUriResolver,
                                                                  RequestMappingHandlerMapping requestMappingHandlerMapping,
                                                                  PaginationHandlerMethodRegistry paginationHandlerMethodRegistry,
                                                                  UniPageProperties properties) {
        return new PaginationStatementMappingRegistrar(paginationUriResolver, requestMappingHandlerMapping,
                paginationHandlerMethodRegistry, properties);
    }

    @Bean
    @ConditionalOnMissingBean(PaginationUriResolver.class)
    public PaginationUriResolver paginationUriResolver(UniPageProperties properties) {
        DefaultPaginationUriResolver resolver = new DefaultPaginationUriResolver();
        resolver.setBasePath(properties.getPath());
        return resolver;
    }

    @Bean
    @ConditionalOnMissingBean(PaginationComponentFactory.class)
    public PaginationComponentFactory paginationComponentFactory() {
        return new DefaultPaginationComponentFactory();
    }

    @Bean
    @ConditionalOnMissingBean(PaginationHandlerMethodRegistry.class)
    public PaginationHandlerMethodRegistry paginationHandlerMethodRegistry() {
        return new DefaultPaginationHandlerMethodRegistry();
    }

    @Bean
    @ConditionalOnMissingBean(name = "paginationResponseAdvice")
    public PaginationResponseAdvice paginationResponseAdvice(PaginationUriResolver paginationUriResolver,
                                                             List<PaginationActionHandler> actionHandlers) {
        PaginationResponseAdvice advice = new PaginationResponseAdvice();
        advice.setPaginationUrlResolver(paginationUriResolver);
        actionHandlers.forEach(advice::addActionHandler);
        return advice;
    }
}
