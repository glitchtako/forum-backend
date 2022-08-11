package com.glitchtako.forum.model.request;

import lombok.Data;

@Data
public class CreateArticleRequest {

    private Long categoryId;

    private Long authorId;

    private String title;

    private String content;

}
