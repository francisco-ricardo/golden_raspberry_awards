package com.gra.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "release_year")
    private Integer year;

    private String producers;
    private boolean winner;

    // Constructors

    public Movie() {}

    public Movie(String title, Integer year, String producers, boolean winner) {
        this.title = title;
        this.year = year;
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

    public boolean isWinner() {
        return this.winner;
    }

}