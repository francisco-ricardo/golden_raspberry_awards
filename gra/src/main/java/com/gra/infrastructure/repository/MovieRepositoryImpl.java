package com.gra.infrastructure.repository;

import com.gra.domain.model.Movie;
import com.gra.domain.repository.MovieRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * Implementacao do repositorio de filmes utilizando Panache.
 * Fornece metodos para buscar, listar, salvar e deletar filmes.
 * 
 */
@ApplicationScoped
public class MovieRepositoryImpl implements MovieRepository, PanacheRepository<Movie> {

    @Override
    public Optional<Movie> findByIdOptional(Long id) {
        return find("id", id).firstResultOptional();
    }

    @Override
    public List<Movie> listAllMovies() {
        return listAll();
    }

    @Override
    public List<Movie> findAllWinnerMovies() {
        return find("winner", true).list();
    }    

    @Override
    @Transactional
    public void save(Movie movie) {
        persist(movie);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Movie movie = findById(id);
        if (movie != null) {
            delete(movie);
        }
        return movie != null;
    }


}