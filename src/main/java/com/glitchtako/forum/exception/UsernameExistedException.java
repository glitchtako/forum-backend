package com.glitchtako.forum.exception;

import com.glitchtako.forum.constant.ErrorCode;

public class UsernameExistedException extends BaseException {

  public UsernameExistedException() {
    super.code = ErrorCode.USERNAME_EXISTED_EXCEPTION.code;
    super.status = ErrorCode.USERNAME_EXISTED_EXCEPTION.status;
    super.message = "Username already existed";
  }
}
