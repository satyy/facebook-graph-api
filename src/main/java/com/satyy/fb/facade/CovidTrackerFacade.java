package com.satyy.fb.facade;

import com.satyy.fb.exception.ServerException;
import com.satyy.fb.model.CovidStateData;
import com.satyy.fb.service.CovidDetailService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CovidTrackerFacade {

    @Autowired
    private CovidDetailService service;

    public List<CovidStateData> getStateData() throws ServerException {
        return service.getStateData();
    }
}
