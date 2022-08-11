package com.glitchtako.forum.repository;

import javax.persistence.EntityManager;

public interface BaseRepository {

    EntityManager getEntityManager();

}
