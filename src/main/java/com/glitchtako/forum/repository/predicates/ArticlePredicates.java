package com.glitchtako.forum.repository.predicates;

import com.glitchtako.forum.model.entity.QArticle;
import com.querydsl.core.types.dsl.BooleanExpression;

public class ArticlePredicates {

  public static final QArticle article = QArticle.article;

  public static BooleanExpression category(Long categoryId) {
    return categoryId == null ? article.isNotNull() : article.category.id.eq(categoryId);
  }

  public static BooleanExpression likeTitle(String title) {
    return title == null ? article.isNotNull() : article.title.eq(title);
  }

  public static BooleanExpression author(Long authorId) {
    return authorId == null ? article.isNotNull() : article.author.id.eq(authorId);
  }
}
