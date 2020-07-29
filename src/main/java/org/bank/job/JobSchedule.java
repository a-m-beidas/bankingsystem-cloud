package org.bank.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobSchedule {


    @Scheduled(fixedRate = 5000)
    public void scheduled() {
        System.out.println("Scheduled Job");
    }
}
