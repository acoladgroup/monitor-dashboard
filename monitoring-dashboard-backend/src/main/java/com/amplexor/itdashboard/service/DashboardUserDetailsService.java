package com.amplexor.itdashboard.service;

import com.amplexor.itdashboard.dao.UserDao;
import com.amplexor.itdashboard.model.User;
import com.amplexor.itdashboard.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Created by marquesh on 13/08/2018.
 */
@Service
public class DashboardUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> user = null;

        if (StringUtils.isStringInt(s))
            user = userDao.findByUserId(new BigInteger(s));
        else
            user = userDao.findOneByLogin(s);

        if(!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

        return user.get();
    }
}