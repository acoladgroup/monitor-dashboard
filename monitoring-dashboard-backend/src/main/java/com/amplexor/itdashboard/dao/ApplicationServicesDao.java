package com.amplexor.itdashboard.dao;

import com.amplexor.itdashboard.model.ApplicationServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ApplicationServicesDao extends JpaRepository<ApplicationServices, BigInteger> {

}
