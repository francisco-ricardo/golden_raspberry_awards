package com.gra.api;

import java.util.List;

import com.gra.domain.model.Movie;
import com.gra.domain.service.MovieService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/movies/winners")
public class MovieWinnerResource {

    private final MovieService movieService;


    @Inject
    public MovieWinnerResource(MovieService movieService) {
        this.movieService = movieService;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> listWinnerMovies() {
        return movieService.findAllWinnerMovies();        
    }

}
