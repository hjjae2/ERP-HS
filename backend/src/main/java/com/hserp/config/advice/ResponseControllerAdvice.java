package com.hserp.config.advice;

import com.hserp.vo.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        String[] excludedClassNames = {"ExceptionControllerAdvice"};

        for(String excludedClassName : excludedClassNames) {
           if(excludedClassName.equalsIgnoreCase(returnType.getContainingClass().getSimpleName())) {
               return false;
           }
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body != null && body.getClass().equals(ApiResponse.class)) {
            return body;
        }
        return ApiResponse.success(body);
    }
}
