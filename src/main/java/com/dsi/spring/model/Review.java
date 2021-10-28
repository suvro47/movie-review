package com.dsi.spring.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Review {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long reviewId;
        private String content;
        private double movieRating;
        private Integer likes;
        @ElementCollection
        private List<String> comments;

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public double getMovieRating() {
                return movieRating;
        }

        public void setMovieRating(double movieRating) {
                this.movieRating = movieRating;
        }

        public Integer getLikes() {
                return likes;
        }

        public void setLikes(Integer likes) {
                this.likes = likes;
        }

        public List<String> getComments() {
                return comments;
        }

        public void setComments(List<String> comments) {
                this.comments = comments;
        }
}
