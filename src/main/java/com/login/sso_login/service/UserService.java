package com.login.sso_login.service;

import com.login.sso_login.model.User;

/**
 * @author liurong
 * @date 2020/10/24 20:53
 */
public interface UserService {

    User getUser(String userName, String passWord);
}
