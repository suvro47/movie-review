package com.dsi.spring.dao;

import java.util.List;

import com.dsi.spring.model.Cast;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastDao extends JpaRepository <Cast, Long> {

    @Override
    List<Cast> findAll();
    
}
