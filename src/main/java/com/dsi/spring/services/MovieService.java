package com.dsi.spring.services;

import java.util.List;

import com.dsi.spring.model.Movie;

import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    void addMovie(Movie movie);

    List<Movie> getMovies();
}
