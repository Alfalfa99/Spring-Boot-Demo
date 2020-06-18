package com.alfalfa.demo.dao;

import com.alfalfa.demo.domain.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    @Select("SELECT r.* FROM role r,user_role ru WHERE r.`id` = ru.`role_id` AND ru.`user_id` = #{uid}")
    List<Role> getRolesByUid(Long uid);

    @Select("SELECT * FROM role WHERE url = #{url}")
    List<Role> getListBypath(String url);
}
