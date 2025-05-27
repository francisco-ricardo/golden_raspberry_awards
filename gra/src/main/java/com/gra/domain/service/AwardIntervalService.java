package com.gra.domain.service;

import com.gra.domain.model.AwardInterval;
import java.util.List;

/**
 * Interface que define os metodos de servico para calcular intervalos de vitoria
 * de produtores em premios.
 * Permite buscar os menores e maiores intervalos de vitoria.
 */
public interface AwardIntervalService {
    List<AwardInterval> findMinIntervals();
    List<AwardInterval> findMaxIntervals();
}