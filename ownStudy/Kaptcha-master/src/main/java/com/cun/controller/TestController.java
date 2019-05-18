package com.cun.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.impl.DefaultKaptcha;

@Controller
public class TestController {
    
    // 验证码工具
    @Autowired
    DefaultKaptcha defaultKaptcha;
    
    @RequestMapping("/aadefaultKaptcha") // 生成验证码
    public void defaultKaptcha(HttpServletRequest request,HttpServletResponse response) throws IOException {
        byte[] kaptchaJpeg = null;
        ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
        String createContext = defaultKaptcha.createText();
        request.getSession().setAttribute("initCode", createContext);
        BufferedImage bfImage = defaultKaptcha.createImage(createContext);
        try {
            ImageIO.write(bfImage, "jpg", baoStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 定义respone输出类型为image和jpeg类型，使用respone输出流输出图片
        kaptchaJpeg = baoStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Prama", "no-Cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream sos = response.getOutputStream();
        sos.write(kaptchaJpeg);
        sos.flush();
        sos.close();
    }
    
    // 校队验证码
    @RequestMapping("/vertifyaadefaultKaptcha")
    public ModelAndView vertifyaadefaultKaptcha(HttpServletRequest request,HttpServletResponse response) {
        ModelAndView view = new ModelAndView();
        String initCode = (String) request.getSession().getAttribute("initCode"); 
        String tryCode = request.getParameter("tryCode");
        if (!initCode.equals(tryCode)) {
            view.addObject("info", "错误的验证码");
            view.setViewName("index");
        } else {
            view.addObject("info", "恭喜您");
            view.setViewName("success");
        }
        return view;
    }
    
    
}

