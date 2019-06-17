package com.amplexor.itdashboard.service;

import com.amplexor.itdashboard.model.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface DatabaseService {

    Optional<DatabaseStatusResponse> getDatabaseStatus() throws Exception;
    Optional<BackupStatusResponse> getBackupStatus() throws Exception;
    Optional<List<TableSpace>> getTableSpace() throws Exception;
}
