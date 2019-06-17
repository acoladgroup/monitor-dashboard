package com.amplexor.itdashboard.service;

import com.amplexor.itdashboard.dao.*;
import com.amplexor.itdashboard.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    private DatabaseStatusDao databaseStatusDao;

    @Autowired
    private BackupStatusDao backupStatusDao;

    @Autowired
    private TableSpaceDao tableSpaceDao;

    @Override
    public Optional<DatabaseStatusResponse> getDatabaseStatus() {

        List<DatabaseStatus> databaseStatusList = databaseStatusDao.findAll();
        DatabaseStatusResponse databaseStatusResponse = new DatabaseStatusResponse();
        databaseStatusResponse.setDatabaseStatusList(databaseStatusList);
        return Optional.ofNullable(databaseStatusResponse);
    }

    @Override
    public Optional<BackupStatusResponse> getBackupStatus() {

        List<BackupStatus> backupStatusList = backupStatusDao.findAll();
        BackupStatusResponse backupStatusResponse = new BackupStatusResponse();
        backupStatusResponse.setBackupStatusList(backupStatusList);
        return Optional.ofNullable(backupStatusResponse);
    }

    @Override
    public Optional<List<TableSpace>> getTableSpace() {
        List<TableSpace> tableSpaceList = tableSpaceDao.findAll();
        return Optional.ofNullable(tableSpaceList);
    }
}
