package com.amplexor.itdashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="AGGREGATE_SERVICE_STATUS_VW")
public class ApplicationServices {

    //private String targetGUI;
    @Id
    @Column(name = "ID")
    private Long id; // The row number!

    @Column(name = "TARGET_NAME")
    private String targetName;

    @Column(name = "AVAILABILITY_STATUS")
    private String status;

    @Column(name = "START_TIMESTAMP")
    private Date startTimestamp;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "LINK")
    private String link;

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getStatus() {
        if(status.equals("Target Up"))
            status="Target_Up";
        else if(status.equals("Target Down"))
            status="Target_Down";
        else if(status.equals("Pending/Unknown"))
            status="Pending_Unknown";
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
