package com.dsi.spring.controller;

import com.dsi.spring.dao.ReviewDao;
import com.dsi.spring.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies/{movie_id}/review")
public class ReviewController {
        @Autowired
        private ReviewDao reviewDao;

        @GetMapping("/new/")
        public String addNewReview(Review review){
                reviewDao.save(review);
                return "Review Added";
        }

        @GetMapping("/edit/{review_id}")
        public String editReview(@PathVariable(value = "review_id") Long review_id, Review reviewInfo){

                Review review = reviewDao.findById(review_id).orElse(new Review());
                review.setContent(reviewInfo.getContent());
                review.setComments(reviewInfo.getComments());
                review.setLikes(reviewInfo.getLikes());
                review.setMovieRating(reviewInfo.getMovieRating());
//                review.setUser();

                reviewDao.save(review);
                return "Review Edited";
        }

        @GetMapping("/delete/{review_id}")
        public String deleteReview(@PathVariable(value = "movie_id") Long movie_id, @PathVariable(value = "review_id") Long review_id){
                reviewDao.deleteById(review_id);
                return "redirect:/movies/preview/"+movie_id;
        }
}
