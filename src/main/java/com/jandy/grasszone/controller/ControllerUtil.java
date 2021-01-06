package com.jandy.grasszone.controller;

import com.jandy.grasszone.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;

public class ControllerUtil {
    public static boolean IsSignedIn(HttpServletRequest req) {
        UserDTO userDTO = (UserDTO)req.getSession().getAttribute("user");
        System.out.println(userDTO);
        return userDTO != null;
    }
}
