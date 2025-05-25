package com.gra.domain.service;

import com.gra.domain.model.AwardInterval;
import com.gra.domain.repository.AwardRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@RequiredArgsConstructor
public class AwardIntervalServiceImpl implements AwardIntervalService {

    private final AwardRepository awardRepository;

    @Override
    public List<AwardInterval> findMinIntervals() {
        return calculateIntervals().get("min");
    }

    @Override
    public List<AwardInterval> findMaxIntervals() {
        return calculateIntervals().get("max");
    }

    private Map<String, List<AwardInterval>> calculateIntervals() {
        
        List<AwardInterval> allIntervals = new ArrayList<>();
        Map<String, List<Integer>> producerWins = awardRepository.findAllProducerWins();

        for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
            List<Integer> years = entry.getValue();
            Collections.sort(years);
            for (int i = 1; i < years.size(); i++) {
                int interval = years.get(i) - years.get(i - 1);
                allIntervals.add(new AwardInterval(entry.getKey(), interval, years.get(i - 1), years.get(i)));
            }
        }

        // Find global min and max among consecutive intervals
        int min = allIntervals.stream().mapToInt(AwardInterval::getInterval).min().orElse(Integer.MAX_VALUE);
        int max = allIntervals.stream().mapToInt(AwardInterval::getInterval).max().orElse(Integer.MIN_VALUE);

        List<AwardInterval> minList = new ArrayList<>();
        List<AwardInterval> maxList = new ArrayList<>();
        for (AwardInterval ai : allIntervals) {
            if (ai.getInterval() == min) minList.add(ai);
            if (ai.getInterval() == max) maxList.add(ai);
        }

        Map<String, List<AwardInterval>> result = new HashMap<>();
        result.put("min", minList);
        result.put("max", maxList);
        return result;
    }
}



