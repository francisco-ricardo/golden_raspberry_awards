package com.gra.domain.repository;

import com.gra.domain.model.Movie;
import java.util.List;
import java.util.Optional;


/**
 * Interface que define os metodos de acesso aos dados de filmes.
 * Permite buscar, listar, salvar e deletar filmes no repositório.
 */
public interface MovieRepository {

    Optional<Movie> findByIdOptional(Long id);

    List<Movie> listAllMovies();

    List<Movie> findAllWinnerMovies();

    void save(Movie movie);

    boolean deleteById(Long id);
    
}