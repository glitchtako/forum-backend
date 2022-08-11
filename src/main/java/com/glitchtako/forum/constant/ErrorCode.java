package com.glitchtako.forum.constant;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    USER_NOT_FOUND_EXCEPTION(1000L, HttpStatus.NOT_FOUND),
    USERNAME_EXISTED_EXCEPTION(1001L, HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED_EXCEPTION(1002L, HttpStatus.BAD_REQUEST),

    CATEGORY_NOT_FOUND_EXCEPTION(1003L, HttpStatus.NOT_FOUND),
    ;

    public final long code;
    public final HttpStatus status;

    ErrorCode(long code, HttpStatus status) {
        this.code = code;
        this.status = status;
    }

}
