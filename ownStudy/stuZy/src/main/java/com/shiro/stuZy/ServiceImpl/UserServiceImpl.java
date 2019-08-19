package com.shiro.stuZy.ServiceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.stuZy.Service.UserService;
import com.shiro.stuZy.dao.UserMapper;
import com.shiro.stuZy.entity.User;


/*
 *  没加@Service，报错： No qualifying bean of type 'com.shiro.stuZy.Service.UserService' available: 
 *  expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: 
 *  {@org.springframework.beans.factory.annotation.Autowired(required=true)}
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public Set<String> getRoles(String userName) {
        return userMapper.getRoles(userName);
    }

    @Override
    public Set<String> getPermission(String userName) {
        return userMapper.getPermission(userName);
    }

}
