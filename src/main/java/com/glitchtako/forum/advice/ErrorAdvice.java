package com.glitchtako.forum.advice;

import com.glitchtako.forum.constant.ErrorCode;
import com.glitchtako.forum.exception.BaseException;
import com.glitchtako.forum.model.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ErrorAdvice implements ResponseBodyAdvice<RestResponse> {

    @ExceptionHandler(value = BaseException.class)
    public RestResponse<?> customExceptionHandler(HttpServletRequest req, BaseException ex) {
        log.error(
                "Exception Controller handler - Custom Exception (code: {}, status: {}) is caught for request url {} ",
                ex.getCode(),
                ex.getStatus(),
                req.getRequestURL());
        return RestResponse.fail(ex.getCode(), ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public RestResponse<?> exceptionHandler(HttpServletRequest req, Exception ex) {
        log.error(
                "Exception Controller handler - Exception (message: {}) is caught for request url {} ",
                ex.getMessage(),
                req.getRequestURL());
        return RestResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR.code, ex.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR.status);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public RestResponse<?> exceptionHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException ex) {
        log.error(
                "Exception Controller handler - Method Not Allowed Exception (message: {}) is caught for request url {} ",
                ex.getMessage(),
                req.getRequestURL());
        return RestResponse.fail(ErrorCode.METHOD_NOT_ALLOWED.code, ex.getMessage(), ErrorCode.METHOD_NOT_ALLOWED.status);
    }

    @ExceptionHandler(value = HttpClientErrorException.Forbidden.class)
    public RestResponse<?> exceptionHandler(HttpServletRequest req, HttpClientErrorException.Forbidden ex) {
        log.error(
                "Exception Controller handler - Forbidden Exception (message: {}) is caught for request url {} ",
                ex.getMessage(),
                req.getRequestURL());
        return RestResponse.fail(ErrorCode.FORBIDDEN.code, ex.getMessage(), ErrorCode.FORBIDDEN.status);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public RestResponse<?> exceptionHandler(HttpServletRequest req, BadCredentialsException ex) {
        log.error(
                "Exception Controller handler - Bad Credential Exception (message: {}) is caught for request url {} ",
                ex.getMessage(),
                req.getRequestURL());
        return RestResponse.fail(ErrorCode.UNAUTHORIZED.code, ex.getMessage(), ErrorCode.UNAUTHORIZED.status);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType().isAssignableFrom(RestResponse.class);
    }

    @Override
    public RestResponse beforeBodyWrite(RestResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) return null;

        if (body.getHttpStatus() != null)
            response.setStatusCode(body.getHttpStatus());

        return body;
    }
}
