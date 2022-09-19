package com.glitchtako.forum.repository;

import com.glitchtako.forum.model.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {}
