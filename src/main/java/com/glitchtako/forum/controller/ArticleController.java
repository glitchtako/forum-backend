package com.glitchtako.forum.controller;

import com.glitchtako.forum.exception.ArticleNotFoundException;
import com.glitchtako.forum.exception.CategoryNotFoundException;
import com.glitchtako.forum.exception.UserNotFoundException;
import com.glitchtako.forum.model.dto.PagedDTO;
import com.glitchtako.forum.model.entity.Article;
import com.glitchtako.forum.model.entity.ArticleComment;
import com.glitchtako.forum.model.request.CreateArticleCommentRequest;
import com.glitchtako.forum.model.request.CreateArticleRequest;
import com.glitchtako.forum.model.request.PageArticleRequest;
import com.glitchtako.forum.model.request.PageArticleCommentRequest;
import com.glitchtako.forum.model.response.RestResponse;
import com.glitchtako.forum.service.ArticleCommentService;
import com.glitchtako.forum.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController extends BaseController {

  @Autowired private ArticleService articleService;

  @Autowired private ArticleCommentService articleCommentService;

  @GetMapping(value = "/article/{id}")
  public RestResponse<Article> getArticleById(@PathVariable Long id)
      throws ArticleNotFoundException {
    return RestResponse.ok(this.articleService.getArticleById(id));
  }

  @GetMapping(value = "/articles")
  public ResponseEntity<PagedDTO<Article>> pageArticles(PageArticleRequest request) {
    return null;
  }

  @PostMapping(value = "/article")
  @ResponseStatus(code = HttpStatus.CREATED)
  public RestResponse<Article> createArticle(@RequestBody CreateArticleRequest request)
      throws UserNotFoundException, CategoryNotFoundException {
    return RestResponse.ok(this.articleService.createArticle(request));
  }

  @GetMapping(value = "/article/{id}/comments")
  public RestResponse<PagedDTO<ArticleComment>> pageComments(
      @PathVariable Long id, PageArticleCommentRequest request) {
    return RestResponse.ok(this.articleCommentService.getPagedArticleComments(id, request));
  }

  @PostMapping(value = "/article/{id}/comment")
  public RestResponse<ArticleComment> createComment(
      @PathVariable Long id, @RequestBody CreateArticleCommentRequest request)
      throws UserNotFoundException, ArticleNotFoundException {
    Long userId = this.getPrincipalDTO().getUserId();
    return RestResponse.ok(this.articleCommentService.createArticleComment(id, userId, request));
  }
}
