package com.gra.api;

import com.gra.api.dto.MovieCreateRequest;
import com.gra.api.dto.MovieUpdateRequest;
import com.gra.domain.model.Movie;
import com.gra.domain.service.MovieService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


/**
 * Resource para gerenciar filmes.
 * Fornece endpoints para listar, criar, modificar e deletar filmes.
 */
@Path("/movies")
public class MovieResource {
    
    private final MovieService movieService;

    @Inject
    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> listMovies() {
        return movieService.listAllMovies();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMovie(MovieCreateRequest request) {
        Movie movie = movieService.createMovie(
            request.title,
            request.year,
            request.studios,
            request.producers,
            request.winner
        );
        return Response.status(Response.Status.CREATED).entity(movie).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteMovie(@PathParam("id") Long id) {
        movieService.deleteMovie(id);
        return Response.noContent().build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieById(@PathParam("id") Long id) {
        return movieService.findById(id)
                .map(movie -> Response.ok(movie).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }    
    

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMovie(@PathParam("id") Long id, MovieUpdateRequest request) {
        return movieService.updateMovie(id, request.title, request.year, request.studios, request.producers, request.winner)
                .map(updated -> Response.ok(updated).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

}
