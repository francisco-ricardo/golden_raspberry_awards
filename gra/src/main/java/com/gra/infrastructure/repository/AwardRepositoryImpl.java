package com.gra.infrastructure.repository;

import com.gra.domain.repository.AwardRepository;
import com.gra.domain.model.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.*;

/**
 * Implementacao da interface AwardRepository que fornece metodos para obter dados relacionados 
 * com as premiacoes.
 */
@ApplicationScoped
public class AwardRepositoryImpl implements AwardRepository {
    
    private final MovieRepositoryImpl movieRepository;

    @Inject
    public AwardRepositoryImpl(MovieRepositoryImpl movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public Map<String, List<Integer>> findAllProducerWins() {

        List<Movie> winningMovies = movieRepository.find("winner", true).list();

        Map<String, List<Integer>> producerWins = new HashMap<>();

        for (Movie movie : winningMovies) {
            String producer = movie.getProducers();
            Integer year = movie.getYear();
            if (producer == null || year == null) continue;

            producer = producer.trim();
            if (producer.isEmpty()) continue;

            producerWins.computeIfAbsent(producer, k -> new ArrayList<>()).add(year);
        }

        return producerWins;
    }
}
