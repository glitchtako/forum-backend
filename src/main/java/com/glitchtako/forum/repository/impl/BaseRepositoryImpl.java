package com.glitchtako.forum.repository.impl;

import com.glitchtako.forum.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BaseRepositoryImpl implements BaseRepository {

    @PersistenceContext private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
