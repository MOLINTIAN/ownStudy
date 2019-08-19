package com.shiro.stuZy.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.stuZy.Service.UserService;
import com.shiro.stuZy.entity.User;

public class UserRealm extends AuthorizingRealm {
    
    @Autowired
    private UserService userService;
    
    /**
         * 为当前登陆的用户授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        // 获取用户名
        String userName = (String) principals.getPrimaryPrincipal();
        // 创建授权验证对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 授予角色
        simpleAuthorizationInfo.setRoles(userService.getRoles(userName));
        // 授予权限
        simpleAuthorizationInfo.setStringPermissions(userService.getPermission(userName));
        return simpleAuthorizationInfo;
    }

    /**
         * 为当前登陆的用户进行身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        // 获取用户名
        String userName = (String) token.getPrincipal();
        // 数据库数据获取
        User user = userService.getUserByUserName(userName);
        if (user != null) {
            // 进行身份验证,主要是密码（可涉及加密解密密码对比）
           AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), "");
           return authenticationInfo;
        }
        // 失败返回null
        return null;
    }

}
