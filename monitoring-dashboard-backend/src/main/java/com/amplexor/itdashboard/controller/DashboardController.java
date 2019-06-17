package com.amplexor.itdashboard.controller;

import com.amplexor.itdashboard.model.BackupStatusResponse;
import com.amplexor.itdashboard.model.DashboardResponse;
import com.amplexor.itdashboard.model.DatabaseStatusResponse;
import com.amplexor.itdashboard.model.TableSpace;
import com.amplexor.itdashboard.service.DashboardService;
import com.amplexor.itdashboard.service.DatabaseService;
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
public class DashboardController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard() {
        try {
            //String userId = principal.getName();

            logger.debug("getDashboard : " + "Request for getDashboard");

            //taskService.listTasks(new BigInteger(userId), taskType, getPageable(sortColumn, sortDirection, pageIndex, pageSize));
            Optional<DashboardResponse> dashboardResponse = dashboardService.getDashboard();
            return ResponseEntity.ok(dashboardResponse);

        } catch (Exception e) {
            logger.error("getDashboard", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
