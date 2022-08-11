package com.glitchtako.forum.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageArticleRequest extends PageRequest {

    private String title;

}
