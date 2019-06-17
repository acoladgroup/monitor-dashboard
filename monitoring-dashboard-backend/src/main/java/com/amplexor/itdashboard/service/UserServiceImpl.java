package com.amplexor.itdashboard.service;

import com.amplexor.itdashboard.dao.UserDao;
import com.amplexor.itdashboard.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Created by marquesh on 13/08/2018.
 */
@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Optional<User> findByUserId(BigInteger userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    public Optional<User> findOneByLogin(String login) {
        return userDao.findOneByLogin(login);
    }


}
