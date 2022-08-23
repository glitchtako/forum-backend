package com.glitchtako.forum.service;

import com.glitchtako.forum.exception.ArticleNotFoundException;
import com.glitchtako.forum.model.entity.Article;
import com.glitchtako.forum.repository.ArticleRepository;
import com.glitchtako.forum.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @InjectMocks
    private ArticleService articleService = new ArticleServiceImpl();

    @Mock
    private ArticleRepository articleRepository;

    @Test
    public void testGetArticleById() throws ArticleNotFoundException {

        Article article = Article.builder()
                .id(1000L)
                .build();
        Mockito.when(this.articleRepository.findById(1000L)).thenReturn(Optional.of(article));
        Article result = this.articleService.getArticleById(1000L);

        Assertions.assertEquals(article, result);
    }

}
