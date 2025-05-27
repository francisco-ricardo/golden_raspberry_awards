package com.gra.domain.service;

import com.gra.domain.model.AwardInterval;
import com.gra.domain.repository.AwardRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;


/**
 * Service para calcular os intervalos de premios entre os anos em que os produtores ganharam.
 * Ele calcula os intervalos minimos e maximos entre os premios ganhos por cada produtor.
 * Os intervalos sao calculados considerando uma janela deslizante de 1 ano.
 */
@ApplicationScoped
public class AwardIntervalServiceImpl implements AwardIntervalService {

    private static final Logger LOG = Logger.getLogger(AwardIntervalServiceImpl.class);

    private final AwardRepository awardRepository;

    @Inject
    public AwardIntervalServiceImpl(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }


    @Override
    public List<AwardInterval> findMinIntervals() {
        return calculateIntervals().get("min");
    }

    @Override
    public List<AwardInterval> findMaxIntervals() {
        return calculateIntervals().get("max");
    }


    /**
     * Calcula os intervalos de premios entre os anos em que os produtores ganharam.
     * O metodo percorre todos os produtores e seus anos de premiacao,
     * calcula os intervalos entre os anos de premiacao
     * e retorna um mapa com os intervalos minimos e maximos.
     * 
     * @return um mapa com os intervalos minimos e maximos, onde a chave "min"
     *         corresponde aos intervalos minimos e a chave "max" corresponde aos intervalos maximos.
     *         Se nao houver intervalos, o mapa retornara vazio.
     * 
     * Exemplo de calculo de intervalos:    
     * 
    1900    1999    2008    2009                      2000  2018    2019    2099
    l       r                                         l     r
            l       r                                       l       r
                    l       r                                       l       r 

    producer: Producer 1                             producer: Producer 2
    previous: 1900                                   previous: 2000
    following: 1999                                  following: 2018 
    interval: 99                                     interval: 18

    producer: Producer 1                             producer: Producer 2
    previous: 1999                                   previous: 2018
    following: 2008                                  following: 2019
    interval: 9                                      interval: 1

    producer: Producer 1                             producer: Producer 2
    previous: 2008                                   previous: 2019 
    following: 2009                                  following: 2099
    interval: 1                                      interval: 80
     *    
     * 
     *         Exemplo de retorno:
     *         {
     *             "min": [
     *                 { "producer": "Producer 1", "interval": 1, "previousWin": 2008, "followingWin": 2009 },
     *                 { "producer": "Producer 2", "interval": 1, "previousWin": 2018, "followingWin": 2019 }
     *             ],
     *             "max": [
     *                 { "producer": "Producer 1", "interval": 99, "previousWin": 1900, "followingWin": 1999 }     
     *             ]
     *         }
     * 
    */
    private Map<String, List<AwardInterval>> calculateIntervals() {

        LOG.info("Calculando os intervalos das premiacoes...");
        
        final int slidingWindow = 1; // Define o tamanho da janela deslizante (1 ano)

        // Obtem todos os produtores e os anos em que ganharam premios
        final Map<String, List<Integer>> producerWins = awardRepository.findAllProducerWins();

        // Ordena os anos de cada premiacao para poder calcular os intervalos corretamente
        for (List<Integer> years : producerWins.values()) {
            Collections.sort(years);
        }
        
        final List<AwardInterval> awardIntervalTemp= new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
            final String producer = entry.getKey();
            final List<Integer> years = entry.getValue();

            if (years.size() < slidingWindow + 1) {
                LOG.infof("Produtor %s tem menos do que %d premiacoes...", producer, slidingWindow + 1);
                continue;
            }

            // Calcula os intervalos entre os anos de premiacao
            // A janela deslizante permite calcular os intervalos entre os anos de premiacao
            // Os anos devem estar ordenados para que os intervalos sejam calculados corretamente
            // Exemplo: se os anos forem [2000, 2005, 2010], os intervalos serao [5, 5]            
            for (int i = 0; i < years.size() - slidingWindow; i++) {
                final int interval = years.get(i + slidingWindow) - years.get(i);
                final int previousWin = years.get(i);
                final int followingWin = years.get(i + slidingWindow);            
                awardIntervalTemp.add(new AwardInterval(producer, interval, previousWin, followingWin));
            }
        }

        // Ordena os intervalos para a identificacao dos minimos e maximos
        Collections.sort(awardIntervalTemp, (a1, a2) -> Integer.compare(a1.getInterval(), a2.getInterval()));        

        final Map<String, List<AwardInterval>> result = new HashMap<>(); // Map para armazenar os resultados
        final List<AwardInterval> awardMinIntervals = new ArrayList<>(); // List para armazenar os intervalos minimos
        final List<AwardInterval> awardMaxIntervals = new ArrayList<>(); // List para armazenar os intervalos maximos

        // Verifica se existem intervalos de premios
        if (awardIntervalTemp == null || awardIntervalTemp.isEmpty()) {
            LOG.warn("Nao foram encontrados intervaloes de premiacoes.");
            return result;
        }        

        // Se houver somente um intervalo, ele sera considerado o minimo e o maximo        
        // Se houver mais de um intervalo, encontra o minimo e o maximo

        // Encontra o minimo AwardInterval

        // Somente 1 intervalo
        if (awardIntervalTemp.size() == 1) {            
            awardMinIntervals.add(awardIntervalTemp.get(0));
        } 
        // Mais de 1 intervalo
        else {
            awardMinIntervals.add(awardIntervalTemp.get(0)); // Adiciona o menor intervalo

            // Adiciona todos os intervalos que sao iguais ao primeiro
            for (int i = 1; i < awardIntervalTemp.size(); i++) {
                if (awardIntervalTemp.get(i).getInterval() == awardIntervalTemp.get(0).getInterval()) {
                    awardMinIntervals.add(awardIntervalTemp.get(i));
                } else {
                    break; // Para quando encontrar um intervalo diferente
                }
            }
        }

        // Encontra o maximo AwardInterval
        final int lastIndex = awardIntervalTemp.size() - 1;
        // Somente 1 intervalo
        if (awardIntervalTemp.size() == 1) {
            awardMaxIntervals.add(awardIntervalTemp.get(lastIndex));
        } 
        // Mais de 1 intervalo
        else {
            awardMaxIntervals.add(awardIntervalTemp.get(lastIndex)); // Adiciona o maior intervalo

            // Adiciona todos os intervalos que sao iguais ao primeiro
            for (int i = lastIndex - 1; i >= 0; i--) {
                if (awardIntervalTemp.get(i).getInterval() == awardIntervalTemp.get(lastIndex).getInterval()) {
                    awardMaxIntervals.add(awardIntervalTemp.get(i));
                } else {
                    break; // Para quando encontrar um intervalo diferente
                }
            }
        }

        result.put("min", awardMinIntervals);
        result.put("max", awardMaxIntervals);
        return result;

    }
    
}



