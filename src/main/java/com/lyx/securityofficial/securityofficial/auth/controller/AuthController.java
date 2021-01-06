package com.lyx.securityofficial.securityofficial.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthController {


    @RequestMapping("/home")
    public String home(){
        return "index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    //getUser
    public String getUserName(){
        String userName = "";
        //根据上下文获取登录用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(principal == null){
            userName = "";
        }
        if(principal instanceof UserDetails){
            userName = ((UserDetails) principal).getUsername();
        }else{
            userName = principal.toString();
        }
        return userName;
    }
}
