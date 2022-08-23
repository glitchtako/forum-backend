package com.glitchtako.forum.exception;

import com.glitchtako.forum.constant.ErrorCode;

public class EmailExistedException extends BaseException {

    public EmailExistedException() {
        super.code = ErrorCode.EMAIL_EXISTED_EXCEPTION.code;
        super.status = ErrorCode.EMAIL_EXISTED_EXCEPTION.status;
        super.message = "Email already existed";
    }



}
