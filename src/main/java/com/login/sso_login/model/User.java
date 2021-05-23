package com.login.sso_login.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private int id;

    private String userName;

    private String passWord;

    private Date createDate;

    private Date updateDate;

    private String headImageUrl;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", headImageUrl=" + headImageUrl +
                '}';
    }
}
