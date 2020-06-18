package com.alfalfa.demo.service;

import com.alfalfa.demo.dao.RoleDao;
import com.alfalfa.demo.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 15423
 */
@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    public List<Role> getRolesByUid(Long uid){
        return roleDao.getRolesByUid(uid);
    }

    public List<Role> getListBypath(String url){
        return roleDao.getListBypath(url);
    }
}
