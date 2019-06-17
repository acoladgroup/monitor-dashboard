package com.amplexor.itdashboard.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.stream.Stream;
public class BackupStatusResponse {

    final String COMPLETED = "COMPLETED";
    final String FAILED = "FAILED";
    final String COMPLETED_WITH_WARNINGS = "COMPLETED_WITH_WARNINGS";
    final String RUNNING = "RUNNING";

    private List<BackupStatus> backupStatusList;

    private long backupCompleted;
    private long backupFailed;
    private long backupCompletedWithWarnings;
    private long backupRunning;

    public long getBackupRunning() {
        return getBackupStatusList().stream().filter(x -> x.getStatus().equals(RUNNING)).count();
    }

    public void setBackupRunning(long backupRunning) {
        this.backupRunning = backupRunning;
    }

    public long getBackupCompleted() {
        return getBackupStatusList().stream().filter(x -> x.getStatus().equals(COMPLETED)).count();
    }

    public void setBackupCompleted(long backupCompleted) {
        this.backupCompleted = backupCompleted;
    }

    public long getBackupFailed() {
        return getBackupStatusList().stream().filter(x -> x.getStatus().equals(FAILED)).count();
    }

    public void setBackupFailed(long backupFailed) {
        this.backupFailed = backupFailed;
    }

    public long getBackupCompletedWithWarnings() {
        return getBackupStatusList().stream().filter(x -> x.getStatus().equals(COMPLETED_WITH_WARNINGS)).count();
    }

    public void setBackupCompletedWithWarnings(long backupCompletedWithWarnings) {
        this.backupCompletedWithWarnings = backupCompletedWithWarnings;
    }

    public List<BackupStatus> getBackupStatusList() {
        if(backupStatusList == null)
            backupStatusList = new ArrayList<BackupStatus>();

        backupStatusList.sort(Comparator.comparing(BackupStatus::getStatus).reversed());
        return backupStatusList;
    }

    public void setBackupStatusList(List<BackupStatus> backupStatusList) {
        this.backupStatusList = backupStatusList;
    }

}
