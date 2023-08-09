package com.vti.shoppee.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TestSchedule {
    @Scheduled(cron = "0 0/1 * * * *")
    public void test(){
        System.out.println("Job run at: " + Instant.now());
    }
}
