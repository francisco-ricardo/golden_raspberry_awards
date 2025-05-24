package com.gra.infrastructure.repository;

import com.gra.domain.model.Movie;
import com.gra.domain.repository.MovieRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieRepositoryImpl implements MovieRepository, PanacheRepository<Movie> {

    @Override
    public Optional<Movie> findByIdOptional(Long id) {
        return PanacheRepository.super.findByIdOptional(id);
    }

    @Override
    public List<Movie> listAllMovies() {
        return listAll();
    }

    @Override
    public void save(Movie movie) {
        persist(movie);
    }
}