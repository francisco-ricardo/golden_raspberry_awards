package com.gra.domain.repository;

import java.util.List;
import java.util.Map;

public interface AwardRepository {
    /**
     * Returns a map where the key is the producer name and the value is a list of years they won.
     */
    Map<String, List<Integer>> findAllProducerWins();
}

