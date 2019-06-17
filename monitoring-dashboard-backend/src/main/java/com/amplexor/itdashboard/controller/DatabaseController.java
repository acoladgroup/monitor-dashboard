package com.amplexor.itdashboard.controller;

import com.amplexor.itdashboard.model.*;
import com.amplexor.itdashboard.service.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"})
public class DatabaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DatabaseService databaseService;

    @GetMapping("/databasestatus")
    public ResponseEntity<?> getDatabaseStatus() {
        try {
            //String userId = principal.getName();

            logger.debug("getDatabaseStatus : " + "Request for getDatabaseStatus");

            //taskService.listTasks(new BigInteger(userId), taskType, getPageable(sortColumn, sortDirection, pageIndex, pageSize));
            Optional<DatabaseStatusResponse> databaseStatusResponse = databaseService.getDatabaseStatus();
            return ResponseEntity.ok(databaseStatusResponse);

        } catch (Exception e) {
            logger.error("getDatabaseStatus", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/backupstatus")
    public ResponseEntity<?> getBackupStatus() {
        try {
            //String userId = principal.getName();

            logger.debug("getBackupStatus : " + "Request for getBackupStatus");

            //taskService.listTasks(new BigInteger(userId), taskType, getPageable(sortColumn, sortDirection, pageIndex, pageSize));
            Optional<BackupStatusResponse> backupStatusResponse = databaseService.getBackupStatus();
            return ResponseEntity.ok(backupStatusResponse);

        } catch (Exception e) {
            logger.error("getDatabaseStatus", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/tablespace")
    public ResponseEntity<?> getTableSpace() {
        try {
            //String userId = principal.getName();

            logger.debug("getTableSpace : " + "Request for getTableSpace");

            //taskService.listTasks(new BigInteger(userId), taskType, getPageable(sortColumn, sortDirection, pageIndex, pageSize));
            Optional<List<TableSpace>> tableSpaceList = databaseService.getTableSpace();
            return ResponseEntity.ok(tableSpaceList);

        } catch (Exception e) {
            logger.error("getTableSpace", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
