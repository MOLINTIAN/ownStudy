package com.shiro.stuZy.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shiro.stuZy.realm.UserRealm;

@Configuration
public class ShiroConfig {
    
    /**
         * 注入UserRealm
     * @return
     */
    @Bean
    public UserRealm getUserRealm() {
        return new UserRealm();
    }

    /**
         * 创建ShiroFilterFactoryBean过滤器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        // 创建对象
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全会话管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 添加shiro内置过滤器
        /**
                 *  前面已经提及过很多过滤器：
                 *  anon: 无需认证登陆可以访问
                 *  authc: 认证才可以访问
                 *  user: 如果使用rememberMe功能可以直接访问
                 *  perms： 该资源必须得到资源权限才可以访问
                 *  roles: 该资源必须得到角色权限才可以访问
         */
        // 创建map,保存过滤器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        /*************设置匿名访问请求,不需要登陆可访问***************/
        filterChainDefinitionMap.put("/users/toLogin", "anon"); // 去到登陆界面
        filterChainDefinitionMap.put("/users/login", "anon"); // 登陆请求
        /*************设置身份验证,验证可访问***************/
        filterChainDefinitionMap.put("/users/addUser", "authc"); // 添加操作
        filterChainDefinitionMap.put("/users/updateUser", "authc"); // 修改操作
        
        /*************设置角色验证roles[角色名],验证可访问***************/
        filterChainDefinitionMap.put("/users/addUser", "roles[admin]");
        filterChainDefinitionMap.put("/users/updateUser", "roles[teacher]");
        // 多个角色使用逗号隔开 下面注释掉，因为和上面的会冲突。
        // filterChainDefinitionMap.put("/users/updateUser", "roles[admin], roles[teacher]");
        // 同时拥有这两种权限
        // filterChainDefinitionMap.put("/users/updateUser", "roles[admin,teacher]");
        // 授予权限
        filterChainDefinitionMap.put("/users/updateUser", "perms[user:*]");
        // 放到所有之后
        filterChainDefinitionMap.put("/**", "authc"); // 所有请求
        // 设置过滤器链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        // 设置登陆页面请求，不设置就去到login.jsp页面
        shiroFilterFactoryBean.setLoginUrl("/users/toLogin");
        // 设置权限不足返回的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/users/unAuthc");
        // 返回
        return shiroFilterFactoryBean;
    }
    
    /**
         * 创建DefaultWebSecurityManager，关联realm
     * @param userRealm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm) {
        // 创建DefaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 关联realm
        defaultWebSecurityManager.setRealm(userRealm);
        // 返回
        return defaultWebSecurityManager;
    }
}
