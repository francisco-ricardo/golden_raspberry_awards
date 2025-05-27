package com.gra.domain.service;

import com.gra.domain.model.Movie;
import java.util.List;
import java.util.Optional;

/**
 * Interface que define os metodos de servico para gerenciar filmes.
 * Permite listar, criar, atualizar e deletar filmes, bem como buscar filmes vencedores.
 */
public interface MovieService {

    List<Movie> listAllMovies();

    List<Movie> findAllWinnerMovies();

    Movie createMovie(String title, int year, String studios, String producers, boolean winner);

    void deleteMovie(Long id);

    Optional<Movie> findById(Long id);
    
    Optional<Movie> updateMovie(Long id, String title, int year, String studios, String producers, boolean winner);

}
