package com.shiro.stuZy.Service;

import java.util.Set;

import com.shiro.stuZy.entity.User;

public interface UserService {

    /**
         * 根据用户名查询
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);
    
    /**
         * 根据用户名查询角色
     * @param userName
     * @return
     */
    Set<String> getRoles(String userName);
    
    /**
         * 根据用户名查询权限
     * @param userName
     * @return
     */
    Set<String> getPermission(String userName);
}
