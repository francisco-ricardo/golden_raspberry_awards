package com.gra.api;

import com.gra.domain.model.Movie;
import com.gra.domain.repository.MovieRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/movies")
public class MovieResource {
    
    private final MovieRepository movieRepository;


    @Inject
    public MovieResource(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> listMovies() {
        return movieRepository.listAllMovies();
    }
}
