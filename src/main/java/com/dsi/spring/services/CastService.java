package com.dsi.spring.services;

import java.util.List;

import com.dsi.spring.model.Cast;

import org.springframework.stereotype.Service;

@Service
public interface CastService {
    void createCast(Cast cast);

    List<Cast> getCasts();

}
