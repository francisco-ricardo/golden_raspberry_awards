package com.gra.domain.service;

import java.util.List;

import com.gra.domain.model.Movie;
import com.gra.domain.repository.MovieRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MovieServiceImpl implements MovieService {


    private final MovieRepository movieRepository;

    @Inject
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> listAllMovies() {
        return movieRepository.listAllMovies();
    }

    @Override
    public List<Movie> findAllWinnerMovies() {
        return movieRepository.findAllWinnerMovies();
    }

}
