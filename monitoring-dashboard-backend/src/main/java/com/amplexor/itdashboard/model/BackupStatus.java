package com.amplexor.itdashboard.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="RMAN_BKP_RPT_VW",schema="sysman")
public class BackupStatus {

    @Id
    @Column(name = "ID")
    private Long id; // The row number!

    @Column(name = "LIFECYCLE_STATUS")
    private String lifeCycleStatus;

    @Column(name = "LOB")
    private String lineOfBusiness;

    @Column(name = "DB_NAME")
    private String targetName;

    @Column(name = "BACKUP_TYPE")
    private String backupType;

    @Column(name = "INCR_LEVEL")
    private String incrementalLevel;

    @Column(name = "START_TIME")
    private Date startTime;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TIME_TAKEN")
    private String timeTaken;

    @Column(name = "INPUT_SIZE")
    private String inputSize;

    @Column(name = "OUTPUT_SIZE")
    private String outputSize;

    @Column(name = "DAYS_AGO")
    private String daysAgo;

    @Column(name = "DAY")
    private String day;

    @Column(name = "COLLECTION_TIME")
    private Date collectionTime;


    public String getBackupType() {
        return backupType;
    }

    public void setBackupType(String backupType) {
        this.backupType = backupType;
    }

    public String getIncrementalLevel() {
        return incrementalLevel;
    }

    public void setIncrementalLevel(String incrementalLevel) {
        this.incrementalLevel = incrementalLevel;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        if(status.equals("COMPLETED WITH WARNINGS"))
            return "COMPLETED_WITH_WARNINGS";
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getInputSize() {
        return inputSize;
    }

    public void setInputSize(String inputSize) {
        this.inputSize = inputSize;
    }

    public String getOutputSize() {
        return outputSize;
    }

    public void setOutputSize(String outputSize) {
        this.outputSize = outputSize;
    }

    public String getDaysAgo() {
        return daysAgo;
    }

    public void setDaysAgo(String daysAgo) {
        this.daysAgo = daysAgo;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getLifeCycleStatus() {
        return lifeCycleStatus;
    }

    public void setLifeCycleStatus(String lifeCycleStatus) {
        this.lifeCycleStatus = lifeCycleStatus;
    }

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
