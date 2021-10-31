package com.dsi.spring.services.impl;

import java.util.List;

import com.dsi.spring.dao.CastDao;
import com.dsi.spring.model.Cast;
import com.dsi.spring.services.CastService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CastServiceImpl implements CastService {

    @Autowired
    private CastDao castDao;

    @Override
    public void createCast(Cast cast) {
        castDao.save(cast);
    }

    @Override
    public List<Cast> getCasts() {

        return castDao.findAll();
    }

}
