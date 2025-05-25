package com.gra.domain.service;

import com.gra.domain.model.AwardInterval;
import java.util.List;

public interface AwardIntervalService {
    List<AwardInterval> findMinIntervals();
    List<AwardInterval> findMaxIntervals();
}