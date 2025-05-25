package com.gra.infrastructure.startup;

import com.gra.domain.model.Movie;
import com.gra.domain.repository.MovieRepository;
import com.gra.infrastructure.csv.MovieCsvParser;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@ApplicationScoped
public class MovieDataLoader {

    private static final Logger LOG = Logger.getLogger(MovieDataLoader.class);

    //private final MovieRepository movieRepository;
    //private final MovieCsvParser movieCsvParser;

    @Inject
    MovieRepository movieRepository;

    @Inject
    MovieCsvParser movieCsvParser;


    //@Inject
    //public MovieDataLoader(MovieRepository movieRepository, MovieCsvParser movieCsvParser) {
        //this.movieRepository = movieRepository;
        //this.movieCsvParser = movieCsvParser;
    //}

    @PostConstruct
    void loadData() {
        LOG.info("Loading movies from CSV file...");
        try (Reader reader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("data/movies.csv"))) {
            List<Movie> movies = movieCsvParser.parseFromReader(reader);
            for (Movie movie : movies) {
                movieRepository.save(movie);
                LOG.infof("Loaded movie: %s (%d) - Producers: %s - Winner: %s",
                        movie.getTitle(), movie.getYear(), movie.getProducers(), movie.isWinner());
            }
            LOG.infof("Total movies loaded: %d", movies.size());
        } catch (Exception e) {
            LOG.error("Failed to load movies from CSV", e);
        }
    }
}