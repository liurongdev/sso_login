package com.login.sso_login.service;

import com.login.sso_login.model.User;

import java.util.List;

/**
 * @author liurong
 * @date 2020/10/24 20:53
 */
public interface UserService {

    User getUser(String userName, String passWord);

    List<User> getUserByName(String userName);

    void registry(User user) throws Exception;
}
