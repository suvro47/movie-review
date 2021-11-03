package com.dsi.spring.controller;

import java.util.List;
import com.dsi.spring.model.Actor;
import com.dsi.spring.model.Movie;
import com.dsi.spring.model.Review;
import com.dsi.spring.service.ActorService;
import com.dsi.spring.service.MovieService;

import com.dsi.spring.service.ReviewService;
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
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String getMovies(Model model) {
        List<Movie> movies = movieService.getMovies();
        System.out.println(movies.toString());
        model.addAttribute("movies", movies);
        return "admin/movie/movies";
    }

    @GetMapping("/add")
    public String addMovieForm(Model model) {
        List<Actor> actors = actorService.getActors();
        model.addAttribute("actors", actors);
        model.addAttribute("movieForm", new Movie());
        return "admin/movie/movie_form";
    }

    @PostMapping("/add")
    public String addMovie(Movie movie) {

        movieService.saveMovie(movie);
        return "redirect:/admin/movies/";
    }

    // shows update form
    @GetMapping("/edit/{id}")
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

    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable("id") long id, @Validated Movie movie, BindingResult result, Model model) {
        if (result.hasErrors()) {
            movie.setId(id);
            return "admin/movie/movie_form";
        }

        movieService.saveMovie(movie);
        return "redirect:/admin/movies/";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") long id, Model model) {
        try {
            Movie movie = movieService.getMovieById(id);
            movieService.deleteMovie(movie);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return "redirect:/admin/movies/";
    }

    @GetMapping("/preview/{id}")
    public String getSingleMovie(@PathVariable(value = "id") Long id, Model model){
        try {
            Movie movie = movieService.getMovieById(id);
            model.addAttribute("movie", movie);
            model.addAttribute("reviews", movie.getReviews());
            model.addAttribute("new_review", new Review());
            model.addAttribute("update_review", reviewService.getSingleReview(Integer.toUnsignedLong(8)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "movie/movie_preview";
    }
}
