package com.dsi.spring.controller;

import java.util.List;
import com.dsi.spring.model.Actor;
import com.dsi.spring.model.Movie;
import com.dsi.spring.services.ActorService;
import com.dsi.spring.services.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;

    @RequestMapping("/")
    public String getMovies(Model model) {
        List<Movie> movies = movieService.getMovies();
        System.out.println(movies.toString());
        model.addAttribute("movies", movies);
        return "admin/movie/movies";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addMovieForm(Model model) {
        List<Actor> actors = actorService.getActors();
        model.addAttribute("actors", actors);
        model.addAttribute("movieForm", new Movie());
        return "admin/movie/movie_form";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addMovie(Movie movie) {

        movieService.saveMovie(movie);
        return "redirect:/admin/movies/";
    }

    // shows update form
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String updateMovieForm(@PathVariable("id") long id, Model model) {
        List<Actor> actors = actorService.getActors();
        model.addAttribute("actors", actors);

        try {
            Movie movie = movieService.getMovieById(id);
            model.addAttribute("movieForm", movie);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return "admin/movie/movie_form";
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.POST)
    public String updateMovie(@PathVariable("id") long id, @Validated Movie movie, BindingResult result, Model model) {
        if (result.hasErrors()) {
            movie.setId(id);
            return "admin/movie/movie_form";
        }

        movieService.saveMovie(movie);
        return "redirect:/admin/movies/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") long id, Model model) {
        try {
            Movie movie = movieService.getMovieById(id);
            movieService.deleteMovie(movie);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return "redirect:/admin/movies/";
    }
}
