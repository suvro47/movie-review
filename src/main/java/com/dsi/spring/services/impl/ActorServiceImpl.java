package com.dsi.spring.services.impl;

import java.util.List;

import com.dsi.spring.dao.ActorDao;
import com.dsi.spring.model.Actor;
import com.dsi.spring.services.ActorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorDao actorDao;

    @Override
    public void createActor(Actor cast) {
        actorDao.save(cast);

    }

    @Override
    public List<Actor> getActors() {
        return actorDao.findAll();
    }

}
