package com.jandy.grasszone.controller;

import com.jandy.grasszone.dao.PostDAO;
import com.jandy.grasszone.dto.PostDTO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.jandy.grasszone.dto.UserDTO;
import com.jandy.grasszone.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController

@MapperScan(basePackages = "com.jandy.grasszone.dao")
public class RestInfoController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PostDAO postDAO;

    @GetMapping(value="/time")
    public String Time(){
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    @GetMapping(value = "/users")
    public List<UserDTO> users() throws Exception {
        return userDAO.selectUsers(new UserDTO());
    }

    @ResponseBody
    @PostMapping(value="/submitpost")
    public String SubmitPost(PostDTO postDTO, HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO)session.getAttribute("user");

        if(ControllerUtil.IsSignedIn(req) != true)
            return "fail login";

        postDTO.setAuthor(userDTO.getUserNum());
        postDAO.MakePost(postDTO);
        System.out.println("submitting...");
        System.out.println(postDTO.toString());
        return "success";
    }
    @ResponseBody
    @PostMapping(value="/createuser")
    public String CreateUser(UserDTO userDTO) throws Exception {
        if(userDTO.getName() == "" || userDTO.getPassword() == "")
            return "fail empty";
        if(userDAO.FindUserWithID(userDTO).size() != 0)
            return "fail id";
        userDAO.CreateUser(userDTO);
        if(userDAO.FindUserWithID(userDTO).size() == 0)
            return "fail";
        System.out.println("creating...");
        System.out.println(userDTO);
        return "success";
    }

    @ResponseBody
    @PostMapping(value = "/signin")
    public String SignIn(UserDTO userDTO, HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        List<UserDTO> result = userDAO.SignIn(userDTO);
        if(result.size() != 1)
        {
            return "fail";
        }
        else
        {
            session.setAttribute("user", result.get(0));
            return "success";
        }
    }
}
