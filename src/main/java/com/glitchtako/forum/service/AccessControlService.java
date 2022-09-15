package com.glitchtako.forum.service;

import org.springframework.security.core.Authentication;

public interface AccessControlService {

    boolean checkUserId(Authentication authentication, Long userId);

    boolean checkArticleAuthor(Authentication authentication, Long articleId);
}
