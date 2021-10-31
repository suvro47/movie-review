package com.dsi.spring.controller;

import java.util.List;

import com.dsi.spring.dao.CastDao;
import com.dsi.spring.dao.MovieDao;
import com.dsi.spring.model.Cast;
import com.dsi.spring.model.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/movies")
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private CastDao castDao;

    @GetMapping("/")
    public String getMovies(Model model) {
        List<Movie> movies = movieDao.findAll();
        model.addAttribute("movies", movies);
        return "admin/movie/movies";
    }

    @GetMapping("/add")
    public String addMovieForm(Model model) {
        List<Cast> casts = castDao.findAll();
        model.addAttribute("casts", casts);
        model.addAttribute("movieForm", new Movie());
        return "admin/movie/add_movie_form";
    }

    @PostMapping("/add")
    public String addMovie(Movie movie) {
        movieDao.save(movie);
        return "redirect:/admin/movies/";
    }

    // shows update form
    @GetMapping("/edit/{id}")
    public String updateMovieForm(@PathVariable("id") long id, Model model){
        List<Cast> casts = castDao.findAll();
        model.addAttribute("casts", casts);

        Movie movie = movieDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Movie id: " + id));
        model.addAttribute("movieForm", movie);

        return "admin/movie/add_movie_form";
    }

    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable("id") long id, @Validated Movie movie, BindingResult result, Model model){
        if (result.hasErrors()){
            movie.setId(id);
            return "admin/movie/add_movie_form";
        }

        movieDao.save(movie);
        return "redirect:/admin/movies/";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") long id, Model model){
        Movie movie = movieDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Movie id: " + id));
        movieDao.delete(movie);

        return "redirect:/admin/movies/";
    }
}
