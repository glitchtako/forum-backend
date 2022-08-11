package com.glitchtako.forum.controller;

import com.glitchtako.forum.exception.BaseException;
import com.glitchtako.forum.model.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = BaseException.class)
    public RestResponse<?> customExceptionHandler(HttpServletRequest req, BaseException ex) {
        log.error(
                "Exception Controller handler - Custom Exception (code: {}, status: {}) is caught for request url {} ",
                ex.getCode(),
                ex.getStatus(),
                req.getRequestURL());
        return RestResponse.fail(ex.getCode());
    }

}
