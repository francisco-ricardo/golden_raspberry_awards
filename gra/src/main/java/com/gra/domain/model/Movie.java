package com.gra.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer year;
    private String producers;
    private boolean winner;

    // Constructors, getters, setters

    public Movie() {}

    public Movie(String title, Integer year, String producers, boolean winner) {
        this.title = title;
        this.year = year;
        this.producers = producers;
        this.winner = winner;
    }

    // Getters and setters omitted for brevity
}