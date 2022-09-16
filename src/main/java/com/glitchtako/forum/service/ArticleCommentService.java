package com.glitchtako.forum.service;

import com.glitchtako.forum.exception.ArticleNotFoundException;
import com.glitchtako.forum.exception.UserNotFoundException;
import com.glitchtako.forum.model.dto.PagedDTO;
import com.glitchtako.forum.model.entity.ArticleComment;
import com.glitchtako.forum.model.request.CreateArticleCommentRequest;
import com.glitchtako.forum.model.request.PageArticleCommentRequest;

public interface ArticleCommentService {

    PagedDTO<ArticleComment> getPagedArticleComments(Long articleId, PageArticleCommentRequest request);

    ArticleComment createArticleComment(Long articleId, Long userId, CreateArticleCommentRequest request) throws UserNotFoundException, ArticleNotFoundException;
}
