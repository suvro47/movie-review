package com.dsi.spring.controller;

import java.util.List;

import com.dsi.spring.dao.CastDao;
import com.dsi.spring.dao.MovieDao;
import com.dsi.spring.model.Cast;
import com.dsi.spring.model.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private CastDao castDao;

    @GetMapping("/")
    public String getMovies(Model model) {
        List<Movie> movies = movieDao.findAll();
        model.addAttribute("movies", movies);
        return "movie/movies";
    }

    @GetMapping("/add")
    public String addMovieForm(Model model) {
        List<Cast> casts = castDao.findAll();
        model.addAttribute("casts", casts);
        return "movie/add_movie_form";
    }

    @PostMapping("/add")
    public String addMovie(Movie movie) {
        movieDao.save(movie);
        return "redirect:/movies/";
    }
}
