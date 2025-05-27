package com.gra.api;

import com.gra.api.dto.AwardIntervalResponse;
import com.gra.domain.service.AwardIntervalService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Resource para gerenciar os intervalos de premiacao.
 * Fornece endpoints para obter os intervalos minimos e maximos de premiacao.
 */
@Path("/awards")
public class AwardResource {

    private final AwardIntervalService awardIntervalService;
    

    @Inject
    public AwardResource(AwardIntervalService awardIntervalService) {
        this.awardIntervalService = awardIntervalService;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AwardIntervalResponse getAwardIntervals() {
        return new AwardIntervalResponse(
            awardIntervalService.findMinIntervals(),
            awardIntervalService.findMaxIntervals()
        );
    }
}
