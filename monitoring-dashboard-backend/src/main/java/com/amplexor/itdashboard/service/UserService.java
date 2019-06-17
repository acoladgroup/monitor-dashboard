package com.amplexor.itdashboard.service;

import com.amplexor.itdashboard.model.User;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Created by marquesh on 13/08/2018.
 */
public interface UserService {

    Optional<User> findOneByLogin(String login);

    Optional<User> findByUserId(BigInteger userId);
}

