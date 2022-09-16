package com.glitchtako.forum.exception;

import com.glitchtako.forum.constant.ErrorCode;

public class RefreshTokenExpiredException extends BaseException {

  public RefreshTokenExpiredException() {
    super.code = ErrorCode.REFRESH_TOKEN_EXPIRED_EXCEPTION.code;
    super.status = ErrorCode.REFRESH_TOKEN_EXPIRED_EXCEPTION.status;
    super.message = "Refresh token expired";
  }
}
