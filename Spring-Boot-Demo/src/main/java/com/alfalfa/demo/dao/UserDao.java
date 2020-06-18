package com.alfalfa.demo.dao;

import com.alfalfa.demo.domain.Course;
import com.alfalfa.demo.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    /**
     * 找到所有的用户
     * @return
     */
    @Select("SELECT * FROM user")
    List<User> findAllUser();

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    @Select("SELECT * FROM user WHERE username = #{username}")
    User loadUserByUsername(@Param("username") String username);

    /**
     * 通过用户id查询用户信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(@Param("id") Long id);
    /**
     *  注册用户
     * @param user
     */
    @Insert("INSERT INTO user(id,username,password,nickname,gender,tel,course_num,enable,addtime,nullField) " +
            "VALUES(default,#{user.username},#{user.password},#{user.nickname},#{user.gender}," +
            "#{user.tel},#{user.course_num},#{user.enable},#{user.addtime},#{user.nullField})")
    void userRegister(@Param("user") User user);

    /**
     * 查找用户加入的所有课程
     * @param id
     * @return
     */
    @Select("SELECT c.* FROM course c,course_user cu WHERE c.id = cu.course_id AND cu.user_id = #{id}")
    List<Course> findUserCourse(@Param("id") Long id);

    /**
     * 通过用户id删除用户
     * @param id
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUser(@Param("id") Long id);

    /**
     * 修改用户信息
     * @param user
     */
    @Update("UPDATE user SET nickname = #{user.nickname}, tel = #{user.tel},gender = #{user.gender}" +
            " WHERE id = #{user.id}")
    void updateUserDetail(@Param("user") User user);

    /**
     * 用户选课
     * @param cid
     * @param user
     */
    @Insert("INSERT INTO course_user(id,course_id,user_id) VALUES(default,#{cid},#{user.id}")
    void chooseCourse(@Param("cid") Long cid, @Param("user") User user);

    /**
     * 修改密码
     * @param user
     */
    @Update("UPDATE user SET password = #{user.pw} WHERE id = #{user.id}")
    void updatePassword(@Param("user") User user);
}
