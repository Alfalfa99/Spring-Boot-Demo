package com.alfalfa.demo.service;

import com.alfalfa.demo.config.WebSecurityConfig;
import com.alfalfa.demo.dao.RoleDao;
import com.alfalfa.demo.dao.UserDao;
import com.alfalfa.demo.domain.Course;
import com.alfalfa.demo.domain.Role;
import com.alfalfa.demo.domain.User;
import com.alfalfa.demo.utils.DateTimeTransfer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    RoleService roleService;

    /**
     * 用户注册
     *
     * @param user
     */
    public void userRegister(User user) {
        user.setNickname(user.getUsername());
        user.setAddtime(DateTimeTransfer.getFormatTime());
        user.setPassword(new WebSecurityConfig().passwordEncoder().encode(user.getPassword()));
        userDao.userRegister(user);
    }


    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    public List<Course> findUserCourse(Long uid) {
        return userDao.findUserCourse(uid);
    }

    public void updateUserDetail(User user) {
        userDao.updateUserDetail(user);

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.loadUserByUsername(s);
        if (s == null || "".equals(s)) {
            throw new RuntimeException("用户不能为空");
        }
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user != null) {
            //获取该用户所拥有的权限
            List<Role> sysPermissions = roleService.getRolesByUid(user.getId());
            // 声明用户授权
            sysPermissions.forEach(sysPermission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getName());
                grantedAuthorities.add(grantedAuthority);
            });
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), grantedAuthorities);
    }

    public User getUserByUsername(String username) {
        return userDao.loadUserByUsername(username);
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional(rollbackForClassName = "GlobalExceptionHandler.class")
    public void chooseCourse(Long cid, User user) {
        user.setCourse_num(user.getCourse_num() + 1);
        userDao.updateUserDetail(user);
        userDao.chooseCourse(cid, user);
    }

    public void updatePassword(User user) {
        user.setPassword(new WebSecurityConfig().passwordEncoder().encode(user.getPassword()));
        userDao.updatePassword(user);
    }
}
