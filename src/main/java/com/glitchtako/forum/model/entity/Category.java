package com.glitchtako.forum.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(exclude = {"articles"})
@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "category")
  private Set<Article> articles;

  @ManyToMany
  @JoinTable(
      name = "category_moderators",
      joinColumns = @JoinColumn(name = "category_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<User> moderators;

  @Version
  @Column(name = "version")
  private Integer version;

  @CreationTimestamp
  @Column(name = "created_at")
  private Instant createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Instant updatedAt;
}
