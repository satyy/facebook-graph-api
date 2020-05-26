package com.satyy.fb.controller;

import com.satyy.fb.exception.ServerException;
import com.satyy.fb.facade.CovidTrackerFacade;
import com.satyy.fb.model.CovidStateData;
import com.satyy.fb.util.CovidPublishToFb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/covid/fb/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class CovidFacebookGroupController {
    @Autowired
    private CovidPublishToFb publishToFb;

    @PostMapping(value = "/group")
    public ResponseEntity publishToGroup() throws ServerException {
        publishToFb.publishToGroup();
        return ResponseEntity.ok().build();
    }
}
