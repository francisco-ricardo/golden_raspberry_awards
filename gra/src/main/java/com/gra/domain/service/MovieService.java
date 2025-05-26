package com.gra.domain.service;

import com.gra.domain.model.Movie;
import java.util.List;


public interface MovieService {
    List<Movie> listAllMovies();
    List<Movie> findAllWinnerMovies();
}
