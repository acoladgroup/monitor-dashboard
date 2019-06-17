package com.amplexor.itdashboard.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DatabaseStatusResponse {

    private final String STATUS_UP = "Up";
    private final String STATUS_DOWN = "Down";
    private final String STATUS_UNREACHABLE = "Unreachable";
    private final String STATUS_UNKNOWN = "Unknown";
    private final String STATUS_AGENT_DOWN = "Agent Down";
    private final String STATUS_METRIC_ERROR = "Metric Error" ;
    private final String STATUS_BLACKOUT = "Blackout" ;

    private long databaseUnreachable;
    private long databaseUp;
    private long databaseDown;
    private long databaseUnknown;
    private long databaseAgentDown;
    private long databaseMetricError;
    private long databaseBlackout;

    private List<DatabaseStatus> databaseStatusList;

    public long getDatabaseUnknown() {
        return databaseStatusList.stream().filter(x -> x.getDatabaseStatus().equals(STATUS_UNKNOWN)).count();
    }

    public void setDatabaseUnknown(long databaseUnknown) {
        this.databaseUnknown = databaseUnknown;
    }

    public long getDatabaseAgentDown() {
        return databaseStatusList.stream().filter(x -> x.getDatabaseStatus().equals(STATUS_AGENT_DOWN)).count();
    }

    public void setDatabaseAgentDown(long databaseAgentDown) {
        this.databaseAgentDown = databaseAgentDown;
    }

    public long getDatabaseMetricError() {
        return databaseStatusList.stream().filter(x -> x.getDatabaseStatus().equals(STATUS_METRIC_ERROR)).count();
    }

    public void setDatabaseMetricError(long databaseMetricError) {
        this.databaseMetricError = databaseMetricError;
    }

    public long getDatabaseBlackout() {
        return getDatabaseStatusList().stream().filter(x -> x.getDatabaseStatus().equals(STATUS_BLACKOUT)).count();
    }

    public void setDatabaseBlackout(long databaseBlackout) {
        this.databaseBlackout = databaseBlackout;
    }


    public long getDatabaseUp() {
        return getDatabaseStatusList().stream().filter(x -> x.getDatabaseStatus().equals(STATUS_UP)).count();
    }

    public void setDatabaseUp(int databaseUp) {
        this.databaseUp = databaseUp;
    }

    public long getDatabaseDown() {
        return getDatabaseStatusList().stream().filter(x -> x.getDatabaseStatus().equals(STATUS_DOWN)).count();
    }

    public void setDatabaseDown(int databaseDown) {
        this.databaseDown = databaseDown;
    }


    public long getDatabaseUnreachable() {
        return getDatabaseStatusList().stream().filter(x -> x.getDatabaseStatus().equals(STATUS_UNREACHABLE)).count();
    }

    public void setDatabaseUnreachable(int databaseUnreachable) {
        this.databaseUnreachable = databaseUnreachable;
    }

    public List<DatabaseStatus> getDatabaseStatusList() {
        if(databaseStatusList == null)
            databaseStatusList = new ArrayList<DatabaseStatus>();

        databaseStatusList.sort(Comparator.comparing(DatabaseStatus::getDatabaseStatus));
        return databaseStatusList;
    }

    public void setDatabaseStatusList(List<DatabaseStatus> databaseStatusList) {
        this.databaseStatusList = databaseStatusList;
    }
}
