package com.amplexor.itdashboard.controller;

import com.amplexor.itdashboard.model.NetworkInfoResponse;
import com.amplexor.itdashboard.service.NetworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"})
public class NetworkController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NetworkService networkService;

    @GetMapping("/network")
    public ResponseEntity<?> getNetworkInfo() {
        try {
            logger.debug("getNetworkInfo : " + "Request for getNetworkInfo");

            Optional<NetworkInfoResponse> networkInfoResponse = networkService.getNetworkInfo();
            return ResponseEntity.ok(networkInfoResponse);

        } catch (Exception e) {
            logger.error("getDatabaseStatus", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
