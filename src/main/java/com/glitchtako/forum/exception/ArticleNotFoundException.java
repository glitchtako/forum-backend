package com.glitchtako.forum.exception;

import com.glitchtako.forum.constant.ErrorCode;

public class ArticleNotFoundException extends BaseException {

    public ArticleNotFoundException() {
        super.code = ErrorCode.ARTICLE_NOT_FOUND_EXCEPTION.code;
        super.status = ErrorCode.ARTICLE_NOT_FOUND_EXCEPTION.status;
        super.message = "Article not found";
    }



}
