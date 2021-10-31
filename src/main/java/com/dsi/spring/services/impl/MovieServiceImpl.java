package com.dsi.spring.services.impl;

import java.util.List;

import com.dsi.spring.dao.MovieDao;
import com.dsi.spring.model.Movie;
import com.dsi.spring.services.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Override
    public void addMovie(Movie movie) {
        movieDao.save(movie);

    }

    @Override
    public List<Movie> getMovies() {

        return movieDao.findAll();
    }

}
