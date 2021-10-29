package com.dsi.spring.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Review {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long reviewId;
        private String content;
        private Integer movieRating;
        private Integer likes;
        @ElementCollection
        private List<String> comments;

        @OneToOne
        private User user;

        @ManyToOne
        private Movie movie;

        public Long getReviewId() {
                return reviewId;
        }

        public void setReviewId(Long reviewId) {
                this.reviewId = reviewId;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public Integer getMovieRating() {
                return movieRating;
        }

        public void setMovieRating(Integer movieRating) {
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

        public Movie getMovie() {
                return movie;
        }

        public void setMovie(Movie movie) {
                this.movie = movie;
        }
}
