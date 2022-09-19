package com.glitchtako.forum.service;

import com.glitchtako.forum.exception.ArticleNotFoundException;
import com.glitchtako.forum.exception.EndpointAccessDeniedException;
import org.springframework.security.core.Authentication;

public interface AccessControlService {

  boolean checkUserId(Authentication authentication, Long userId);

  boolean checkArticleAuthor(Authentication authentication, Long articleId);
}
