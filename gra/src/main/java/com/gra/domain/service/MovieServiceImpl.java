package com.gra.domain.service;

import java.util.List;
import java.util.Optional;

import com.gra.domain.model.Movie;
import com.gra.domain.repository.MovieRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

/**
 * Implementacao do servico de gerenciamento de filmes.
 * Fornece metodos para listar, criar, atualizar e deletar filmes,
 * bem como buscar filmes vencedores.
 */
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

    @Override
    @Transactional
    public Movie createMovie(String title, int year, String studios, String producers, boolean winner) {
        Movie movie = new Movie(title, year, producers, studios, winner);
        movieRepository.save(movie);
        return movie;
    }    

    @Override
    @Transactional
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findByIdOptional(id);
    }

    @Override
    @Transactional
    public Optional<Movie> updateMovie(Long id, String title, int year, String studios, String producers, boolean winner) {
        Optional<Movie> movieOpt = movieRepository.findByIdOptional(id);
        if (movieOpt.isPresent()) {
            Movie movie = movieOpt.get();
            movie.setTitle(title);
            movie.setYear(year);
            movie.setStudios(studios);
            movie.setProducers(producers);
            movie.setWinner(winner);
            return Optional.of(movie);
        }
        return Optional.empty();
    }    

}
