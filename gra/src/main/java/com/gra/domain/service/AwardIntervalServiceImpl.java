package com.gra.domain.service;

import com.gra.domain.model.AwardInterval;
import com.gra.domain.repository.AwardRepository;
import com.gra.infrastructure.repository.AwardRepositoryImpl;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;

@ApplicationScoped
@RequiredArgsConstructor
public class AwardIntervalServiceImpl implements AwardIntervalService {

    private static final Logger LOG = Logger.getLogger(AwardIntervalServiceImpl.class);

    private final AwardRepository awardRepository;

    @Override
    public List<AwardInterval> findMinIntervals() {
        return calculateIntervals().get("min");
    }

    @Override
    public List<AwardInterval> findMaxIntervals() {
        return calculateIntervals().get("max");
    }



    /**
    0       1       2       3                         0     1       2       3
    1900    1999    2008    2009                      2000  2018    2019    2099
    l       r                                         l     r
            l       r                                       l       r
                    l       r                                       l       r 

    producer: Producer 1                             producer: Producer 2
    previous: 1900                                   previous: 2000
    following: 1999                                  following: 2018 
    interval: 99                                     interval: 18

    producer: Producer 1                             producer: Producer 2
    previous: 1999                                   previous: 2018
    following: 2008                                  following: 2019
    interval: 9                                      interval: 1

    producer: Producer 1                             producer: Producer 2
    previous: 2008                                   previous: 2019 
    following: 2009                                  following: 2099
    interval: 1                                      interval: 80

    */
    private Map<String, List<AwardInterval>> calculateIntervals2() {

        
        final Map<String, List<Integer>> producerWins = awardRepository.findAllProducerWins();
        final int slidingWindow = 1;

        final List<AwardInterval> awardIntervalTemp= new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
            final String producer = entry.getKey();
            final List<Integer> years = entry.getValue();

            if (years.size() < slidingWindow + 1) {
                LOG.infof("Producer %s has less than %d wins, skipping.", producer, slidingWindow + 1);
                continue;
            }

            for (int i = 0; i < years.size() - slidingWindow; i++) {
                final int interval = years.get(i + slidingWindow) - years.get(i);
                final int previousWin = years.get(i);
                final int followingWin = years.get(i + slidingWindow);            
                awardIntervalTemp.add(new AwardInterval(producer, interval, previousWin, followingWin));
            }
        }

        for (AwardInterval ai : awardIntervalTemp) {
            LOG.infof("[UNSORTED] Producer: %s, Interval: %d, Previous Win: %d, Following Win: %d", 
                    ai.getProducer(), ai.getInterval(), ai.getPreviousWin(), ai.getFollowingWin());
        }

        // Sort intervals by interval
        Collections.sort(awardIntervalTemp, (a1, a2) -> Integer.compare(a1.getInterval(), a2.getInterval()));        

        for (AwardInterval ai : awardIntervalTemp) {
            LOG.infof("[SORTED] Producer: %s, Interval: %d, Previous Win: %d, Following Win: %d", 
                    ai.getProducer(), ai.getInterval(), ai.getPreviousWin(), ai.getFollowingWin());
        }

        final Map<String, List<AwardInterval>> result = new HashMap<>();
        final List<AwardInterval> awardIntervals = new ArrayList<>();
        // TODO: VALIDATE EMPTY LIST
        if (awardIntervalTemp.isEmpty()) {
            LOG.warn("No award intervals found.");
            return result;
        }
        
        if (awardIntervalTemp.size() == 1) {
            awardIntervals.add(awardIntervalTemp.get(0)); // Add the first interval    
        } else {
            // Add the first interval
            awardIntervals.add(awardIntervalTemp.get(0));
            // Make sure to add the all intervals that are equal to the first one
            for (int i = 1; i < awardIntervalTemp.size(); i++) {
                if (awardIntervalTemp.get(i).getInterval() == awardIntervalTemp.get(0).getInterval()) {
                    awardIntervals.add(awardIntervalTemp.get(i));
                } else {
                    break; // Stop when we find a different interval
                }
            }

        }
        
        for (AwardInterval ai : awardIntervals) {
            LOG.infof("[FINAL] Producer: %s, Interval: %d, Previous Win: %d, Following Win: %d", 
                    ai.getProducer(), ai.getInterval(), ai.getPreviousWin(), ai.getFollowingWin());
        }


        return result;

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

        LOG.info("Testing new method");
        calculateIntervals2();


        Map<String, List<AwardInterval>> result = new HashMap<>();
        result.put("min", minList);
        result.put("max", maxList);
        return result;
    }
}



