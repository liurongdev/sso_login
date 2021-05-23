package com.login.sso_login.service.impl;

import com.login.sso_login.dao.UserDao;
import com.login.sso_login.model.User;
import com.login.sso_login.service.UserService;
import com.login.sso_login.utils.SecretUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public List<User> getUserByName(String userName) {
        if(StringUtils.isBlank(userName)){
            throw new IllegalArgumentException("userName or passWord cannot be null...");
        }
        return userDao.getUserByName(userName);
    }


    public void login(){

    }


    @Override
    public void registry(User user) throws Exception {
        List<User> userByName = this.getUserByName(user.getUserName());
        if(CollectionUtils.isNotEmpty(userByName)){
            throw new IllegalArgumentException("username already exist");
        }
        int nextId = userDao.getMaxId() + 1;
        Date now = new Date();
        user.setId(nextId);
        user.setPassWord(SecretUtils.AesEncrypt(user.getPassWord()));
        user.setCreateDate(now);
        user.setUpdateDate(now);
        user.setHeadImageUrl(null);
        userDao.insertByUser(user);
    }
}
