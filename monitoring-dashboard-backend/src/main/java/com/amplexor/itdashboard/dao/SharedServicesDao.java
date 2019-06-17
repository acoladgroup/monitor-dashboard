package com.amplexor.itdashboard.dao;

import com.amplexor.itdashboard.model.ApplicationServices;
import com.amplexor.itdashboard.model.SharedServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface SharedServicesDao extends JpaRepository<SharedServices, BigInteger> {

}
