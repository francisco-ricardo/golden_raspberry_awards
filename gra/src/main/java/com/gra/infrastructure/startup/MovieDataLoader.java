package com.gra.infrastructure.startup;

import com.gra.domain.model.Movie;
import com.gra.domain.repository.MovieRepository;
import com.gra.infrastructure.csv.MovieCsvParser;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.jboss.logging.Logger;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;


/**
 * Classe responsavel por carregar os dados dos filmes a partir de um arquivo CSV
 * durante o evento de inicializaca da aplicacao.
 * Utiliza o MovieCsvParser para processar o arquivo e o MovieRepository para persistir os dados.
 */
@ApplicationScoped
public class MovieDataLoader {

    private static final Logger LOG = Logger.getLogger(MovieDataLoader.class);

    private final MovieRepository movieRepository;
    private final MovieCsvParser movieCsvParser;

    @Inject
    public MovieDataLoader(MovieRepository movieRepository, MovieCsvParser movieCsvParser) {
        this.movieRepository = movieRepository;
        this.movieCsvParser = movieCsvParser;
        LOG.info("MovieDataLoader inicializado");
    }


    /**
     * Metodo que observa o evento de inicializacao da aplicacao e carrega os filmes do arquivo CSV.
     * Os filmes sao salvos no repositório e mensagens de log sao geradas para cada filme carregado.
     *
     * @param ev Evento de inicializacao da aplicacao
     */     
    @Transactional
    void onStart(@Observes StartupEvent ev) {
        LOG.info("Carregando filmes do arquivo CSV...");
        try (Reader reader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("data/movielist.csv"))) {
            List<Movie> movies = movieCsvParser.parseFromReader(reader);
            for (Movie movie : movies) {
                movieRepository.save(movie);
                LOG.infof("Filme: %s (%d) - Produtor: %s - Vencedor: %s",
                        movie.getTitle(), movie.getYear(), movie.getProducers(), movie.isWinner());
            }
            LOG.infof("Total de filmes carregados: %d", movies.size());
        } catch (Exception e) {
            LOG.error("Falhou para carregar filmes do CSV", e);
            throw new RuntimeException("Falhou para carregar filmes do CSV", e);
        }
    }
    
}