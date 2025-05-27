package com.gra.domain.model;

/**
 * Representa o intervalo entre vitorias de um produtor em premios.
 * Contem informacoes sobre o produtor, o intervalo de anos entre vitorias,
 * o ano da vitoria anterior e o ano da vitoria seguinte.
 */
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