package com.glitchtako.forum.exception;

import com.glitchtako.forum.constant.ErrorCode;

public class RefreshTokenNotFoundException extends BaseException {

  public RefreshTokenNotFoundException() {
    super.code = ErrorCode.REFRESH_TOKEN_NOT_FOUND_EXCEPTION.code;
    super.status = ErrorCode.REFRESH_TOKEN_NOT_FOUND_EXCEPTION.status;
    super.message = "Refresh token not found";
  }
}
