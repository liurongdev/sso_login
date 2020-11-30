package com.login.sso_login.service.impl;

import com.login.sso_login.dao.UserDao;
import com.login.sso_login.model.User;
import com.login.sso_login.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liurong
 * @date 2020/10/24 20:54
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String userName, String passWord) {
        if(StringUtils.isBlank(userName)|| StringUtils.isBlank(passWord)){
            throw new IllegalArgumentException("userName or passWord cannot be null...");
        }
        return userDao.getUser(userName, passWord);
    }



}
