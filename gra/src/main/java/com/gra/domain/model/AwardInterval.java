package com.gra.domain.model;

public class AwardInterval {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;

    public AwardInterval(String producer, int interval, int previousWin, int followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    // Getters...
    public String getProducer() {
        return this.producer;
    }
    
    public int getInterval() {
        return this.interval;        
    }

    public int getPreviousWin() {
        return this.previousWin;
    }

    public int getFollowingWin() {
        return this.followingWin;
    }
}