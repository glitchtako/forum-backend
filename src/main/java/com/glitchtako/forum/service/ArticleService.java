package com.glitchtako.forum.service;

import com.glitchtako.forum.exception.ArticleNotFoundException;
import com.glitchtako.forum.exception.CategoryNotFoundException;
import com.glitchtako.forum.exception.UserNotFoundException;
import com.glitchtako.forum.model.dto.PagedDTO;
import com.glitchtako.forum.model.entity.Article;
import com.glitchtako.forum.model.request.CreateArticleRequest;
import com.glitchtako.forum.model.request.PageArticleRequest;

public interface ArticleService {

    Article getArticleById(Long articleId) throws ArticleNotFoundException;

    PagedDTO<Article> getPagedArticles(PageArticleRequest request);

    Article createArticle(CreateArticleRequest request) throws CategoryNotFoundException, UserNotFoundException;

}
