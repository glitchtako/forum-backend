package com.glitchtako.forum.service.impl;

import com.glitchtako.forum.exception.ArticleNotFoundException;
import com.glitchtako.forum.exception.CategoryNotFoundException;
import com.glitchtako.forum.exception.UserNotFoundException;
import com.glitchtako.forum.model.dto.PagedDTO;
import com.glitchtako.forum.model.entity.Article;
import com.glitchtako.forum.model.entity.Category;
import com.glitchtako.forum.model.entity.User;
import com.glitchtako.forum.model.request.CreateArticleRequest;
import com.glitchtako.forum.model.request.PageArticleRequest;
import com.glitchtako.forum.repository.ArticleCommentRepository;
import com.glitchtako.forum.repository.ArticleRepository;
import com.glitchtako.forum.repository.CategoryRepository;
import com.glitchtako.forum.repository.UserRepository;
import com.glitchtako.forum.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

  @Autowired private ArticleRepository articleRepository;

  @Autowired private CategoryRepository categoryRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private ArticleCommentRepository articleCommentRepository;

  @Override
  public Article getArticleById(Long articleId) throws ArticleNotFoundException {
    return this.articleRepository.findById(articleId).orElseThrow(ArticleNotFoundException::new);
  }

  @Override
  public PagedDTO<Article> getPagedArticles(PageArticleRequest request) {
    final List<Article> articles =
        this.articleRepository.getPagedArticles(
            request.getCategoryId(),
            request.getAuthorId(),
            request.getTitle(),
            request.getPage(),
            request.getSize());
    return PagedDTO.<Article>builder()
        .page(request.getPage())
        .size(request.getSize())
        .data(articles)
        .build();
  }

  @Override
  public Article createArticle(CreateArticleRequest request)
      throws CategoryNotFoundException, UserNotFoundException {

    Category category =
        this.categoryRepository
            .findById(request.getCategoryId())
            .orElseThrow(CategoryNotFoundException::new);

    User author =
        this.userRepository.findById(request.getAuthorId()).orElseThrow(UserNotFoundException::new);

    Article article =
        Article.builder()
            .title(request.getTitle())
            .category(category)
            .author(author)
            .content(request.getContent())
            .build();

    return articleRepository.save(article);
  }
}
