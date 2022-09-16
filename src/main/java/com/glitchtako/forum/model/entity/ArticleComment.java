package com.glitchtako.forum.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"article"})
@Entity
@Table(name = "article_comments")
public class ArticleComment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "article_id")
  private Article article;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Lob
  @Column(name = "content")
  private String content;

  @CreationTimestamp
  @Column(name = "created_at")
  private Instant createdAt;
}
