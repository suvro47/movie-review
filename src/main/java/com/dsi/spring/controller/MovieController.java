package com.dsi.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class MovieController {
    @GetMapping("/")
    public String getMovies() {
        return "movie/movies";
    }

    @GetMapping("/add")
    public String addMovieForm() {
        return "movie/add_movie_form";
    }

    @PostMapping("/add")
    public String addMovie() {
        return "redirect:/movies/";
    }
}
