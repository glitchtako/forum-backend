package com.glitchtako.forum.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "audit_log")
public class ActivityLog {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "method")
  private String method;

  @Column(name = "operation")
  private String operation;

  @Column(name = "parameters")
  private String parameters;

  @Column(name = "ip")
  private String ip;

  @Column(name = "operating_system")
  private String operatingSystem;

  @Column(name = "process_time")
  private Long processTime;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private Instant createdAt;
}
