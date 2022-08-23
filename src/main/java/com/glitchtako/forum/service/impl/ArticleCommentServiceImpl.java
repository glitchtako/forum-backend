package com.glitchtako.forum.service.impl;

import com.glitchtako.forum.exception.ArticleNotFoundException;
import com.glitchtako.forum.exception.UserNotFoundException;
import com.glitchtako.forum.model.dto.PagedDTO;
import com.glitchtako.forum.model.entity.Article;
import com.glitchtako.forum.model.entity.ArticleComment;
import com.glitchtako.forum.model.entity.User;
import com.glitchtako.forum.model.request.CreateArticleCommentRequest;
import com.glitchtako.forum.model.request.PageArticleCommentRequest;
import com.glitchtako.forum.repository.ArticleCommentRepository;
import com.glitchtako.forum.repository.ArticleRepository;
import com.glitchtako.forum.repository.UserRepository;
import com.glitchtako.forum.service.ArticleCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    private ArticleCommentRepository articleCommentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PagedDTO<ArticleComment> getPagedArticleComments(Long articleId, PageArticleCommentRequest request) {
        final List<ArticleComment> comments = this.articleCommentRepository.getPagedArticleComments(
                articleId,
                request.getPage(),
                request.getSize());
        return PagedDTO.<ArticleComment>builder()
                .page(request.getPage())
                .size(request.getSize())
                .data(comments)
                .build();
    }

    @Override
    public ArticleComment createArticleComment(Long articleId, CreateArticleCommentRequest request) throws UserNotFoundException, ArticleNotFoundException {
        Article article = this.articleRepository.findById(articleId).orElseThrow(ArticleNotFoundException::new);
        User user = this.userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);
        ArticleComment articleComment = ArticleComment.builder()
                .article(article).content(request.getContent()).user(user).build();

        return this.articleCommentRepository.save(articleComment);
    }

}
