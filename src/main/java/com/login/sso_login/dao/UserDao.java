package com.login.sso_login.dao;

import com.login.sso_login.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author liurong
 * @date 2020/10/24 20:46
 */
@Mapper
public interface UserDao {

    @Select("<script>"
            +"select id,name,password,create_date as createDate,update_date as updateDate "
            +" from user where name = #{userName} and password = #{passWord}"
            +"</script>")
    User getUser(@Param("userName") String userName,@Param("passWord") String passWord);
}
