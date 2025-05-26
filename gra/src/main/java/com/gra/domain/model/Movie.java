package com.gra.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)    
    private String title;

    @Column(name = "release_year", nullable = false)
    private Integer year;

    @Column(nullable = false, length = 255)
    private String studios;

    @Column(nullable = false, length = 255)
    private String producers;

    @Column(nullable = false)
    private boolean winner;

    // Constructors

    public Movie() {}

    public Movie(String title, Integer year, String producers, String studios, boolean winner) {
        this.title = title;
        this.year = year;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    // Getters

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getYear() {
        return this.year;
    }

    public String getProducers() {
        return this.producers;
    }

    public String getStudios() {
        return this.studios;
    }

    public boolean isWinner() {
        return this.winner;
    }

}