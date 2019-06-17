package com.amplexor.itdashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="TBS_USAGE_RPT_VIEW",schema="sysman")
public class TableSpace {

    @Id
    @Column(name = "ID")
    private Long id; // The row number!

    @Column(name = "DB_NAME")
    private String databaseName;

    @Column(name = "DB_TYPE")
    private String databaseType;

    @Column(name = "COLLECTION_TIMESTAMP")
    private Date collectionTime;

    @Column(name = "TABLESPACE")
    private String tableSpace;

    @Column(name = "PERCENTAGE_USED")
    private String percentageUsed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getTableSpace() {
        return tableSpace;
    }

    public void setTableSpace(String tableSpace) {
        this.tableSpace = tableSpace;
    }

    public String getPercentageUsed() {
        return percentageUsed;
    }

    public void setPercentageUsed(String percentageUsed) {
        this.percentageUsed = percentageUsed;
    }

}
