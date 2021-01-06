package com.jandy.grasszone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserIDController {
    @GetMapping(value = "/home")
    public String home()
    {
        System.out.println("hihihihi");
        return "home.html";
    }
    @GetMapping(value = "/")
    public String MainPage(HttpServletRequest req)
    {
        return "signin.html";
    }
    @GetMapping(value = "/signup")
    public String SignUp()
    {
        return "signup.html";
    }
    @GetMapping(value = "/signout")
    public String SignOut(HttpServletRequest req){
        HttpSession session = req.getSession();
        session.setAttribute("user", null);
        return "redirect:/";
    }
}
