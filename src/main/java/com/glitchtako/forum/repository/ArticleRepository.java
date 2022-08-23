package com.glitchtako.forum.repository;

import com.glitchtako.forum.model.entity.Article;
import com.glitchtako.forum.model.entity.QArticle;
import com.glitchtako.forum.repository.predicates.ArticlePredicates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, QuerydslPredicateExecutor<Article>, BaseRepository {

    default List<Article> getPagedArticles(Long categoryId, Long authorId, String title, long page, long size) {

        QArticle article = QArticle.article;

        return new JPAQueryFactory(this.getEntityManager())
                .selectFrom(article)
                .where(ArticlePredicates.category(categoryId)
                        .and(ArticlePredicates.author(authorId))
                        .and(ArticlePredicates.likeTitle(title))
                )
                .orderBy(article.id.desc())
                .limit(size)
                .offset(page * size)
                .fetch();

    }

}
