package com.glitchtako.forum.repository;

import com.glitchtako.forum.model.entity.ArticleComment;
import com.glitchtako.forum.model.entity.QArticleComment;
import com.glitchtako.forum.repository.predicates.ArticleCommentPredicates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long>, QuerydslPredicateExecutor<ArticleComment>, BaseRepository {

    default List<ArticleComment> getPagedArticleComments(Long articleId, long page, long size) {
        QArticleComment articleComment = QArticleComment.articleComment;

        return new JPAQueryFactory(this.getEntityManager())
                .selectFrom(articleComment)
                .where(ArticleCommentPredicates.article(articleId))
                .orderBy(articleComment.createdAt.desc())
                .limit(size)
                .offset(page * size)
                .fetch();
    }

}
