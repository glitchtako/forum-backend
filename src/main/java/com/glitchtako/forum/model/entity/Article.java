package com.glitchtako.forum.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "articles")
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private User author;

  @Column(name = "title")
  private String title;

  @Lob
  @Column(name = "content")
  private String content;

  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
  private List<ArticleComment> comments;

  @CreationTimestamp
  @Column(name = "created_at")
  private Instant createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Instant updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Article article = (Article) o;
    return id != null && Objects.equals(id, article.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
