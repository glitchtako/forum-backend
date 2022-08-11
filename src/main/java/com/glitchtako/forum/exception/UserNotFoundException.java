package com.glitchtako.forum.exception;

import com.glitchtako.forum.constant.ErrorCode;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super.code = ErrorCode.USER_NOT_FOUND_EXCEPTION.code;
        super.status = ErrorCode.USER_NOT_FOUND_EXCEPTION.status;
        super.message = "User not found";
    }



}
