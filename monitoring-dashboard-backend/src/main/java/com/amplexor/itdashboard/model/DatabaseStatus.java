package com.amplexor.itdashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DB_STATUS_RPT_VW",schema="sysman")
public class DatabaseStatus {

    //private String targetGUI;
    @Id
    @Column(name = "ID")
    private Long id; // The row number!

    @Column(name = "INSTANCE_NAME")
    private String instanceName;

    @Column(name = "HOST_NAME")
    private String hostName;

    @Column(name = "STATUS")
    private String databaseStatus;

    @Column(name = "TOTAL_MEMORY")
    private int totalMemory;

    @Column(name = "VERSION")
    private String databaseVersion;

    @Column(name = "DATA_GUARD_STATUS")
    private String dataGuardStatus;

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getDatabaseStatus() {
        return databaseStatus;
    }

    public void setDatabaseStatus(String databaseStatus) {
        this.databaseStatus = databaseStatus;
    }

    public String getDatabaseVersion() {
        return databaseVersion;
    }

    public void setDatabaseVersion(String databaseVersion) {
        this.databaseVersion = databaseVersion;
    }

    public String getDataGuardStatus() {
        return dataGuardStatus;
    }

    public void setDataGuardStatus(String dataGuardStatus) {
        this.dataGuardStatus = dataGuardStatus;
    }


    public int getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(int totalMemory) {
        this.totalMemory = totalMemory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
