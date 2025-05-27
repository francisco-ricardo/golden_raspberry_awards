package com.gra.domain.service;

import com.gra.domain.model.Movie;
import java.util.List;
import java.util.Optional;


public interface MovieService {
    List<Movie> listAllMovies();
    List<Movie> findAllWinnerMovies();
    Movie createMovie(String title, int year, String studios, String producers, boolean winner);
    void deleteMovie(Long id);
    Optional<Movie> findById(Long id);
    Optional<Movie> updateMovie(Long id, String title, int year, String studios, String producers, boolean winner);

}
