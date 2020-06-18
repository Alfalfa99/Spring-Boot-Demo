package com.alfalfa.demo.config;

import com.alfalfa.demo.domain.Role;
import com.alfalfa.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 安全元数据源
 */
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    RoleService roleService;
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //查询具体某个接口的权限
        List<Role> permissionList =  roleService.getListBypath(requestUrl);
        Role role = new Role();
        role.setName("管理员");
        if(permissionList == null || permissionList.size() == 0){
            //如果访问的路径是注册接口,则不需要任何权限
            if (requestUrl.startsWith("/Reg")){
                return null;
            } /*else if(requestUrl.endsWith(".html") || requestUrl.startsWith("/image")){
                return null;
            }*/
            //请求路径没有配置权限，表明该请求接口需要管理员权限
//            permissionList.add(role);
            return null;
        }
        //任何路径管理员均可访问
        permissionList.add(role);
        //获取有权访问的角色的名称
        String[] attributes = new String[permissionList.size()];
        for(int i = 0;i<permissionList.size();i++){
            attributes[i] = permissionList.get(i).getName();
        }
        return SecurityConfig.createList(attributes);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
