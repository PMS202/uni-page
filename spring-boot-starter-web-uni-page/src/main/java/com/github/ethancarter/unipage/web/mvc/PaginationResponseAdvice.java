package com.github.ethancarter.unipage.web.mvc;

import com.github.ethancarter.unipage.statement.PaginationStatement;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class PaginationResponseAdvice implements ResponseBodyAdvice<Object> {

    private PaginationUriResolver paginationURIResolver = new DefaultPaginationUriResolver();
    private final List<PaginationActionHandler> actionHandlers = new ArrayList<>();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return PaginationStatement.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType contentType, Class converterType,
                                  ServerHttpRequest serverRequest, ServerHttpResponse serverResponse) {
        if (!(body instanceof PaginationStatement)) {
            return body;
        }
        RequestMapping annotation = returnType.getMethodAnnotation(RequestMapping.class);
        if (annotation == null) {
            return null;
        }
        String[] paginationKeys = annotation.value();
        PaginationStatement statement = (PaginationStatement) body;
        PaginationUriInfo uriInfo = paginationURIResolver.resolve(paginationKeys, serverRequest.getURI().getPath());
        if (uriInfo == null || !uriInfo.isValid()) {
            return null;
        }
        String key = uriInfo.getPaginationKey();
        String action = uriInfo.getAction();
        for (PaginationActionHandler actionHandler : actionHandlers) {
            if (actionHandler.getAction().equals(action)) {
                return actionHandler.handle(key, statement,
                        ((ServletServerHttpRequest) serverRequest).getServletRequest(),
                        ((ServletServerHttpResponse) serverResponse).getServletResponse());
            }
        }
        return null;
    }

    public void addActionHandler(PaginationActionHandler actionHandler) {
        Assert.notNull(actionHandler, "PaginationStatementActionHandler must not be null");
        this.actionHandlers.add(actionHandler);
    }

    public void setPaginationUrlResolver(PaginationUriResolver paginationURIResolver) {
        Assert.notNull(paginationURIResolver, "PaginationUrlResolver must not be null");
        this.paginationURIResolver = paginationURIResolver;
    }
}
