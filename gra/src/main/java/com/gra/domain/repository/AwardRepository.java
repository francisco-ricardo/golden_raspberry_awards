package com.gra.domain.repository;

import java.util.List;
import java.util.Map;


/**
 * Interface que define os metodos de acesso aos dados de premios.
 * Permite buscar informacoes sobre vitorias de produtores em premios.
 */
public interface AwardRepository {
    /**
     * Retorna um mapa onde a chave e o nome do produtor e o valor e uma lista de anos em que ele ganhou.
     */
    Map<String, List<Integer>> findAllProducerWins();
}

