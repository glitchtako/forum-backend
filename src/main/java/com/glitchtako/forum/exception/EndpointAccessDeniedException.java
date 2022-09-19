package com.glitchtako.forum.exception;

import com.glitchtako.forum.constant.ErrorCode;

public class EndpointAccessDeniedException extends BaseException {

  public EndpointAccessDeniedException() {
    super.code = ErrorCode.ENDPOINT_ACCESS_DENIED_EXCEPTION.code;
    super.status = ErrorCode.ENDPOINT_ACCESS_DENIED_EXCEPTION.status;
    super.message = "Endpoint access denied";
  }
}
