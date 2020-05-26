package com.satyy.fb.scheduler;

import com.satyy.fb.exception.ServerException;
import com.satyy.fb.util.CovidPublishToFb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class PublishScheduler {

    @Autowired
    private CovidPublishToFb publishToFb;

    @Scheduled(cron = "0 0 0 * * *")
    public void publish() {
        log.info("Scheduling for publishing current stats to fb group...");
        try {
            publishToFb.publishToGroup();
        } catch (ServerException e) {
            log.error("Error publishing to group during schedule job, {}", e.getMessage());
        }
    }
}
