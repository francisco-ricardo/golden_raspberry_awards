package com.gra.infrastructure.csv;

import com.gra.domain.model.Movie;

import jakarta.enterprise.context.ApplicationScoped;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;


/**
 * Processa um arquivo CSV contendo informacoes sobre filmes.
 * O arquivo deve conter os campos: title, year, producers, studios, winner.
 * O delimitador utilizado e o ponto e vírgula (;).
 * O cabecalho e ignorado e as linhas vazias sao descartadas.
 * Exemplo de linha: "The Godfather";1972;"Francis Ford Coppola, Mario Puzo";"Paramount Pictures";"yes"
 */
@ApplicationScoped
public class MovieCsvParser {


    public MovieCsvParser() {}

    public List<Movie> parseFromReader(Reader source) throws IOException {

        final List<Movie> result = new LinkedList<>();
        final CSVFormat format = CSVFormat.DEFAULT.builder()
            .setDelimiter(';')
            .setHeader()
            .setSkipHeaderRecord(true)
            .setTrim(true)
            .setIgnoreEmptyLines(true)
            .build();
        try (CSVParser records = new CSVParser(source, format)) {

            for (CSVRecord entry : records) {
                result.add(recordToMovie(entry));
            }
        }
        return result;
    }

    private Movie recordToMovie(CSVRecord entry) {
        final String titleField = entry.get("title");
        final int yearField = Integer.parseInt(entry.get("year"));
        final String producersField = entry.get("producers");
        final String studioField = entry.get("studios");
        final boolean winnerField = "yes".equalsIgnoreCase(entry.get("winner"));
        return new Movie(titleField, yearField, producersField, studioField, winnerField);
    }
}