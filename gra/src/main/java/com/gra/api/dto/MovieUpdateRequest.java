package com.gra.api.dto;

public class MovieUpdateRequest {
    
    public String title;
    public int year;
    public String studios;
    public String producers;
    public boolean winner;

    public MovieUpdateRequest() {}

    public MovieUpdateRequest(String title, int year, String studios, String producers, boolean winner) {
        this.title = title;
        this.year = year;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    
    public String getTitle() {
        return title;
    }
    public int getYear() {
        return year;
    }
    public String getStudios() {
        return studios;
    }
    public String getProducers() {
        return producers;
    }
    public boolean isWinner() {
        return winner;
    }
}