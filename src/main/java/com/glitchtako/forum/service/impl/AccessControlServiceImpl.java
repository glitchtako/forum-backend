package com.glitchtako.forum.service.impl;

import com.glitchtako.forum.model.dto.UserDetailsDTO;
import com.glitchtako.forum.model.entity.Article;
import com.glitchtako.forum.repository.ArticleRepository;
import com.glitchtako.forum.service.AccessControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service(value = "accessControlService")
public class AccessControlServiceImpl implements AccessControlService {

  @Autowired private ArticleRepository articleRepository;

  @Override
  public boolean checkUserId(Authentication authentication, Long userId) {

    UserDetailsDTO user = (UserDetailsDTO) authentication.getPrincipal();

    return Objects.equals(user.getUserId(), userId);
  }

  @Override
  public boolean checkArticleAuthor(Authentication authentication, Long articleId) {

    UserDetailsDTO user = (UserDetailsDTO) authentication.getPrincipal();

    Article article = this.articleRepository.findById(articleId).orElse(null);

    if (article == null) {
      return false;
    }

    return Objects.equals(article.getAuthor().getId(), user.getUserId());
  }
}
