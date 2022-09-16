package com.glitchtako.forum.constant;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
  UNAUTHORIZED(401L, HttpStatus.UNAUTHORIZED),
  FORBIDDEN(403L, HttpStatus.FORBIDDEN),
  METHOD_NOT_ALLOWED(405L, HttpStatus.METHOD_NOT_ALLOWED),
  INTERNAL_SERVER_ERROR(500L, HttpStatus.INTERNAL_SERVER_ERROR),

  USER_NOT_FOUND_EXCEPTION(1000L, HttpStatus.NOT_FOUND),
  USERNAME_EXISTED_EXCEPTION(1001L, HttpStatus.BAD_REQUEST),
  EMAIL_EXISTED_EXCEPTION(1002L, HttpStatus.BAD_REQUEST),
  CATEGORY_NOT_FOUND_EXCEPTION(1003L, HttpStatus.NOT_FOUND),
  ARTICLE_NOT_FOUND_EXCEPTION(1004L, HttpStatus.NOT_FOUND),

  REFRESH_TOKEN_EXPIRED_EXCEPTION(2000L, HttpStatus.BAD_REQUEST),

  REFRESH_TOKEN_NOT_FOUND_EXCEPTION(2001L, HttpStatus.NOT_FOUND),
  ;

  public final long code;
  public final HttpStatus status;

  ErrorCode(long code, HttpStatus status) {
    this.code = code;
    this.status = status;
  }
}
