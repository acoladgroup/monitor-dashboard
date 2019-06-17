package com.amplexor.itdashboard.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.amplexor.itdashboard.model.NetworkInfoResponse;
import com.amplexor.itdashboard.service.NetworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    NetworkService networkService;

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay=30000)
    public void retrieveNetworkInfo() throws Exception {
        logger.info("Start retrieveNetworkInfo at {}", dateFormat.format(new Date()));

        networkService.retrieveAndSaveNetworkInfo();

        logger.info("End Successfully retrieveNetworkInfo at {}", dateFormat.format(new Date()));

    }
}