package com.login.sso_login.dao;

import com.login.sso_login.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("<script>"
            +"select id,name,password,create_date as createDate,update_date as updateDate "
            +" from user where name = #{userName}"
            +"</script>")
    List<User> getUserByName(@Param("userName") String userName);

    @Insert("<script>"
            +"insert into user(id,name,password,create_date,update_time,head_image_url)"
            + "values(#{id},#{userName},#{passWord},#{createDate},#{updateTime},#{headImageUrl}) "
            +"</script>")
    int insert(@Param("id") int id,
               @Param("userName") String userName,
               @Param("passWord") String passWord,
               @Param("createDate") String createDate,
               @Param("updateTime") String updateTime,
               @Param("headImageUrl") String headImageUrl);

    @Insert("<script>"
            +"insert into user(id,name,password,create_date,update_date,head_image_url)"
            + "values(#{id},#{userName},#{passWord},#{createDate},#{updateDate},#{headImageUrl})"
            +"</script>")
    int insertByUser(User user);

    @Select("<script>"
            +"select max(id) from user"
            +"</script>")
    int getMaxId();
}
