package com.glitchtako.forum.repository.predicates;

import com.glitchtako.forum.model.entity.QArticle;
import com.glitchtako.forum.model.entity.QArticleComment;
import com.querydsl.core.types.dsl.BooleanExpression;

public class ArticleCommentPredicates {
  public static final QArticleComment articleComment = QArticleComment.articleComment;

  public static BooleanExpression article(Long articleId) {
    return articleComment.article.id.eq(articleId);
  }
}
