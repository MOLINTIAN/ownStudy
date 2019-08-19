package com.shiro.stuZy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shiro.stuZy.entity.User;

@Controller
@RequestMapping("/users")
public class UserController {
    
    @RequestMapping("/addUser")
    public String addUser() {
        // 返回到user文件夹下的addUser.html
        return "/user/addUser";
    }
    
    @RequestMapping("/updateUser")
    public String updateUser() {
        // 返回到user文件夹下的updateUser.html
        return "/user/updateUser";
    }
    
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/login"; // 页面开始出，需要"/"
    }
    
    /*
         * 权限不足访问
     */
    @RequestMapping("/unAuthc")
    public String unAuthc() {
        return "/unAuthc"; // 页面开始出，需要"/"
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, Model model) {
        // 获取当前主体
        Subject subject = SecurityUtils.getSubject();
        // 创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            // 登陆
            subject.login(token);
            // 成功则创建session
            Session session = subject.getSession();
            session.setAttribute("currentUser", user);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", "用户名错误，登陆失败");
            return "/login";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", "密码错误，登陆失败");
            return "/login";
        }
        return "/success";
    }
}
