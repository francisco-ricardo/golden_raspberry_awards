package com.gra.api.dto;

import com.gra.domain.model.AwardInterval;
import java.util.List;

public class AwardIntervalResponse {
    private List<AwardInterval> min;
    private List<AwardInterval> max;

    public AwardIntervalResponse(List<AwardInterval> min, List<AwardInterval> max) {
        this.min = min;
        this.max = max;
    }

    public List<AwardInterval> getMin() { 
        return this.min; 
    }

    public List<AwardInterval> getMax() { 
        return this.max; 
    }
    
}