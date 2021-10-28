package com.dsi.spring.controller;


import com.dsi.spring.dao.ReviewDao;
import com.dsi.spring.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
        @Autowired
        private ReviewDao reviewDao;

        @RequestMapping("/new/logged")
        public String loggedUser(Authentication authentication){
                return "Name : " +authentication.getName() ;
        }

        @RequestMapping("/new/review")
        public String addNewReview(Review review){
                reviewDao.save(review);
                return "Review Added";
        }

        @RequestMapping("/edit/review/{review_id}")
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

        @RequestMapping("/delete/review/{review_id}")
        public String deleteReview(@PathVariable(value = "review_id") Long review_id){
                Review review = reviewDao.findById(review_id).orElse(new Review());
                reviewDao.delete(review);
                return "Review Deleted";
        }
}
