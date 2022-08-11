package com.glitchtako.forum.exception;

import com.glitchtako.forum.constant.ErrorCode;

public class CategoryNotFoundException extends BaseException {

    public CategoryNotFoundException() {
        super.code = ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION.code;
        super.status = ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION.status;
        super.message = "User not found";
    }



}
