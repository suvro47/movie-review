package com.dsi.spring.controller;

import com.dsi.spring.dao.UserDao;
import com.dsi.spring.model.Movie;
import com.dsi.spring.model.Review;
import com.dsi.spring.service.MovieService;
import com.dsi.spring.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies/{movie_id}/review")
public class ReviewController {
        @Autowired
        private ReviewService reviewService;

        @Autowired
        private MovieService movieService;

        @Autowired
        private UserDao userDao;

        @PostMapping("/new/{user_id/")
        public String addNewReview(@PathVariable(value="movie_id") Long movie_id, @PathVariable(value = "user_id") Long user_id, Review review){
                System.out.println("Came to new");
                try{
                        review.setMovie(movieService.getMovieById(movie_id));
                        review.setUser(userDao.findById(user_id).orElseThrow());
                } catch (Exception e) {
                        e.printStackTrace();
                }
                //reviewService.saveNewReview(review);
                return "redirect:/movies/preview/"+movie_id;
        }

        @GetMapping("/edit/{review_id}")
        public String editReview(@PathVariable(value = "review_id") Long review_id, Review reviewInfo){

                Review review = reviewService.getSingleReview(review_id);
                review.setContent(reviewInfo.getContent());
                review.setComments(reviewInfo.getComments());
                review.setLikes(reviewInfo.getLikes());
                review.setMovieRating(reviewInfo.getMovieRating());
//                review.setUser();

                reviewService.saveNewReview(review);
                return "Review Edited";
        }

        @GetMapping("/delete/{review_id}")
        public String deleteReview(@PathVariable(value = "movie_id") Long movie_id, @PathVariable(value = "review_id") Long review_id){
                reviewService.deleteReview(review_id);
                return "redirect:/movies/preview/"+movie_id;
        }
}
