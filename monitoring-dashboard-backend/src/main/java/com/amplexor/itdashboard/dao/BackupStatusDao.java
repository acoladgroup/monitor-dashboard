package com.amplexor.itdashboard.dao;

import com.amplexor.itdashboard.model.BackupStatus;
import com.amplexor.itdashboard.model.DatabaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BackupStatusDao extends JpaRepository<BackupStatus, BigInteger> {

}
