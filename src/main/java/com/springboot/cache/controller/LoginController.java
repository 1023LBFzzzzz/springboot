package com.springboot.cache.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @GetMapping("/login")
    public String setRedisSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("sessionId","100");
        return "success";
    }

    @GetMapping("getSessionId")
    public String getRedisSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionId");
        return sessionId;
    }
}
