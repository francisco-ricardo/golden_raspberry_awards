package com.gra.domain.repository;

import com.gra.domain.model.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    Optional<Movie> findByIdOptional(Long id);

    List<Movie> listAllMovies();

    void save(Movie movie);
    
}