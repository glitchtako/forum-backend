package com.glitchtako.forum.controller;

import com.glitchtako.forum.exception.CategoryNotFoundException;
import com.glitchtako.forum.exception.UserNotFoundException;
import com.glitchtako.forum.model.dto.PagedDTO;
import com.glitchtako.forum.model.entity.Article;
import com.glitchtako.forum.model.request.CreateArticleRequest;
import com.glitchtako.forum.model.request.PageArticleRequest;
import com.glitchtako.forum.model.response.RestResponse;
import com.glitchtako.forum.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/articles")
    public ResponseEntity<PagedDTO<Article>> pageArticles(PageArticleRequest request) {


        return null;
    }

    @PostMapping(value = "/article")
    public RestResponse<Article> createArticle(@RequestBody CreateArticleRequest request) throws UserNotFoundException, CategoryNotFoundException {
        return RestResponse.ok(this.articleService.createArticle(request));
    }

    @PostMapping(value = "/article/{id}/comment")
    public void createComment() {

    }

}
