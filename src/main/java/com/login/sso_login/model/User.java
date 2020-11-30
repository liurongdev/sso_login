package com.login.sso_login.model;


import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int id;

    private String userName;

    private String passWord;

    private Date createDate;

    private Date updateDate;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
