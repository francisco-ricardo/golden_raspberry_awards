package com.gra.domain.model;

import lombok.Value;

@Value
public class AwardInterval {
    String producer;
    int interval;
    int previousWin;
    int followingWin;
}
