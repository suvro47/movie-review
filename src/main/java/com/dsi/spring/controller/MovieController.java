package com.dsi.spring.controller;

import java.util.List;
import com.dsi.spring.model.Actor;
import com.dsi.spring.model.Movie;
import com.dsi.spring.services.ActorService;
import com.dsi.spring.services.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService castService;

    @GetMapping("/")
    public String getMovies(Model model) {
        List<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        return "admin/movie/movies";
    }

    @GetMapping("/add")
    public String addMovieForm(Model model) {
        List<Actor> casts = castService.getActors();
        model.addAttribute("casts", casts);
        model.addAttribute("movieForm", new Movie());
        return "admin/movie/add_movie_form";
    }

    @PostMapping("/add")
    public String addMovie(Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/admin/movies/";
    }
}
