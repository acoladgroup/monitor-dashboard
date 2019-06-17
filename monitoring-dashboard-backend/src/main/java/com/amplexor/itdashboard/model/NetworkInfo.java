package com.amplexor.itdashboard.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="DB_NETWORK_STATUS_INFO")
public class NetworkInfo {

    @Id
    @Column(name = "ID")
    private Long id; // The row number!

    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "SUBSIDIARY_LOCATION")
    private String subsidiaryLocation;

    @Column(name = "MPLS_STATUS")
    private String mplsStatus;
    @Column(name = "MPLS_DOWNLOAD_SPEED")
    private String mplsDownloadSpeed;
    @Column(name = "MPLS_UPLOAD_SPEED")
    private String mplsUploadSpeed;

    @Column(name = "PRIMARY_ISP_STATUS")
    private String primaryISPStatus;
    @Column(name = "PRIMARY_ISP_DOWNLOAD_SPEED")
    private String primaryIPSDownloadSpeed;
    @Column(name = "PRIMARY_ISP_UPLOAD_SPEED")
    private String primaryISPUploadSpeed;

    @Column(name = "SECONDARY_ISP_STATUS")
    private String secondaryISPStatus;
    @Column(name = "SECONDARY_ISP_DOWNLOAD_SPEED")
    private String secondaryISPDownloadSpeed;
    @Column(name = "SECONDARY_ISP_UPLOAD_SPEED")
    private String secondaryISPUploadSpeed;

    @Column(name = "MPLS_SG_STATUS")
    private String mplsSGStatus;
    @Column(name = "MPLS_SG_DOWNLOAD_SPEED")
    private String mplsSGDownloadSpeed;
    @Column(name = "MPLS_SG_UPLOAD_SPEED")
    private String mplsSGUploadSpeed;

    @Column(name = "SDWAN_STATUS")
    private String sdwanStatus;
    @Column(name = "SDWAN_DOWNLOAD_SPEED")
    private String sdwanDownloadSpeed;
    @Column(name = "SDWAN_UPLOAD_SPEED")
    private String sdwanUploadSpeed;

    @Column(name = "RETRIEVE_DATE")
    private Date retrieveDate;

    public Date getRetrieveDate() {
        return retrieveDate;
    }

    public void setRetrieveDate(Date retrieveDate) {
        this.retrieveDate = retrieveDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSubsidiaryLocation() {
        return subsidiaryLocation;
    }

    public void setSubsidiaryLocation(String subsidiaryLocation) {
        this.subsidiaryLocation = subsidiaryLocation;
    }

    public String getMplsStatus() {
        return convertNetworkStatus(this.mplsStatus);
    }

    public void setMplsStatus(String mplsStatus) {
        this.mplsStatus = mplsStatus;
    }

    public String getMplsDownloadSpeed() {
        return convertSpeed(mplsDownloadSpeed,"DOWNLOAD");
    }

    public void setMplsDownloadSpeed(String mplsDownloadSpeed) {
        this.mplsDownloadSpeed = mplsDownloadSpeed;
    }

    public String getMplsUploadSpeed() {
        return convertSpeed(mplsUploadSpeed,"UPLOAD");
    }

    public void setMplsUploadSpeed(String mplsUploadSpeed) {
        this.mplsUploadSpeed = mplsUploadSpeed;
    }

    public String getPrimaryISPStatus() {
        return convertNetworkStatus(primaryISPStatus);
    }

    public void setPrimaryISPStatus(String primaryISPStatus) {
        this.primaryISPStatus = primaryISPStatus;
    }

    public String getPrimaryIPSDownloadSpeed() {
        return convertSpeed(primaryIPSDownloadSpeed,"DOWNLOAD");
    }

    public void setPrimaryIPSDownloadSpeed(String primaryIPSDownloadSpeed) {
        this.primaryIPSDownloadSpeed = primaryIPSDownloadSpeed;
    }

    public String getPrimaryISPUploadSpeed() {
        return convertSpeed(primaryISPUploadSpeed,"UPLOAD");
    }

    public void setPrimaryISPUploadSpeed(String primaryISPUploadSpeed) {
        this.primaryISPUploadSpeed = primaryISPUploadSpeed;
    }

    public String getSecondaryISPStatus() {
        return convertNetworkStatus(secondaryISPStatus);
    }

    public void setSecondaryISPStatus(String secondaryISPStatus) {
        this.secondaryISPStatus = secondaryISPStatus;
    }

    public String getSecondaryISPDownloadSpeed() {
        return convertSpeed(secondaryISPDownloadSpeed,"DOWNLOAD");
    }

    public void setSecondaryISPDownloadSpeed(String secondaryISPDownloadSpeed) {
        this.secondaryISPDownloadSpeed = secondaryISPDownloadSpeed;
    }

    public String getSecondaryISPUploadSpeed() {
        return convertSpeed(secondaryISPUploadSpeed,"UPLOAD");
    }

    public void setSecondaryISPUploadSpeed(String secondaryISPUploadSpeed) {
        this.secondaryISPUploadSpeed = secondaryISPUploadSpeed;
    }

    public String getMplsSGStatus() {
        return convertNetworkStatus(mplsSGStatus);
    }

    public void setMplsSGStatus(String mplsSGStatus) {
        this.mplsSGStatus = mplsSGStatus;
    }

    public String getMplsSGDownloadSpeed() {
        return convertSpeed(mplsSGDownloadSpeed,"DOWNLOAD");
    }

    public void setMplsSGDownloadSpeed(String mplsSGDownloadSpeed) {
        this.mplsSGDownloadSpeed = mplsSGDownloadSpeed;
    }

    public String getMplsSGUploadSpeed() {
        return convertSpeed(mplsSGUploadSpeed,"UPLOAD");
    }

    public void setMplsSGUploadSpeed(String mplsSGUploadSpeed) {
        this.mplsSGUploadSpeed = mplsSGUploadSpeed;
    }

    public String getSdwanStatus() {
        return convertNetworkStatus(sdwanStatus);
    }

    public void setSdwanStatus(String sdwanStatus) {
        this.sdwanStatus = sdwanStatus;
    }

    public String getSdwanDownloadSpeed() {
        return convertSpeed(sdwanDownloadSpeed,"DOWNLOAD");
    }

    public void setSdwanDownloadSpeed(String mplsSGDownloadSpeed) {
        this.sdwanDownloadSpeed = sdwanUploadSpeed;
    }

    public String getSdwanUploadSpeed() {
        return convertSpeed(sdwanUploadSpeed,"UPLOAD");
    }

    public void setSdwanUploadSpeed(String mplsSGUploadSpeed) {
        this.sdwanUploadSpeed = sdwanUploadSpeed;
    }

    private String convertNetworkStatus(String status) {
        if(status != null) {
            if(status.equals("0"))
                status = "UP";
            else if(status.equals("1"))
                status = "WARNING";
            else if(status.equals("2"))
                status = "CRITICAL";
            else if(status.equals("3"))
                status = "UNKNOWN";
            else
                status = "API_UNKNOWN";
        } else {
            status = "NOT_DEFINED";
        }
        return status;
    }

    private String convertSpeed(String speed , String direction) {
        if (speed != null && direction.equals("UPLOAD")) {
            speed = "U: " + speed;
        }
        if (speed != null && direction.equals("DOWNLOAD")) {
            speed = "D: " + speed;
        }
        return speed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
