package com.satyy.fb.controller;

import com.satyy.fb.exception.ServerException;
import com.satyy.fb.facade.CovidTrackerFacade;
import com.satyy.fb.model.CovidStateData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/covid/tracker/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class CovidTrackerController {

    @Autowired
    private CovidTrackerFacade facade;

    @GetMapping(value = "/india", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CovidStateData>> getStateWiseData() throws ServerException {
        final List<CovidStateData> stateData = facade.getStateData();
        return ResponseEntity.ok().body(stateData);
    }
}
