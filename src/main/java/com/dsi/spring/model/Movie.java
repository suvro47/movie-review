package com.dsi.spring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity(name = "movie")
@Table(name = "movie", uniqueConstraints = @UniqueConstraint(name = "movie_name_unique", columnNames = "name"))

public class Movie {

    @Id
    @SequenceGenerator(name = "movie_id_sequence", sequenceName = "movie_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "release_date", nullable = false, columnDefinition = "DATE")
    private Date releaseDate;

    // only for users to see
    @Column(name = "rating", nullable = true, columnDefinition = "Decimal(10,2)")
    private Double rating;

    @Column(name = "genre", nullable = false, columnDefinition = "TEXT")
    private String genre;

    @Column(name = "poster", nullable = true, columnDefinition = "TEXT")
    private String poster;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;

    public Movie() {
    }

    public Movie(String name, Date releaseDate, Double rating, String genre, String poster) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.genre = genre;
        this.poster = poster;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    // List<Cast> casts;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
