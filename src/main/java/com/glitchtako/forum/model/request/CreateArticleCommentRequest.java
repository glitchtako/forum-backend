package com.glitchtako.forum.model.request;

import lombok.Data;

@Data
public class CreateArticleCommentRequest {

    private Long userId;

    private String content;

}
