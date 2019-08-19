package com.shiro.stuZy.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.shiro.stuZy.entity.User;


/*
 * mapper上不加@Mapper 报错: org.apache.ibatis.binding.BindingException: 
 * Invalid bound statement (not found): com.shiro.stuZy.dao.UserMapper.getUserByUserName
 */
@Mapper
public interface UserMapper {

    User getUserByUserName(String userName);
    
    Set<String> getRoles(String userName);
    
    Set<String> getPermission(String userName);
}
