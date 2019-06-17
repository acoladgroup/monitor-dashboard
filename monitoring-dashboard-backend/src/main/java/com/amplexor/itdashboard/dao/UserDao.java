package com.amplexor.itdashboard.dao;

import com.amplexor.itdashboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, BigInteger> {

    Optional<User> findByUserId(BigInteger userId);

    Optional<User> findOneByLogin(String login);
}
