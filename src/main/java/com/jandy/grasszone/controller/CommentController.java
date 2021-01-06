package com.jandy.grasszone.controller;

import com.jandy.grasszone.dao.CommentDAO;
import com.jandy.grasszone.dto.CommentDTO;
import com.jandy.grasszone.dto.UserDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentDAO commentDAO;

    @GetMapping("/comments/{postID}")
    public ModelAndView GetPostComments(@PathVariable("postID") int postID ) throws Exception {
        List<CommentDTO> comments = commentDAO.GetPostComments(postID);
//        System.out.println(comments);
        ModelAndView modelAndView = new ModelAndView("comments.html");
        modelAndView.addObject("comments", comments);
        return modelAndView;
    }
    @PostMapping("/submitcomment")
    public String MakeComment(CommentDTO commentDTO, HttpServletRequest req) throws Exception {
//        System.out.println(commentDTO.toString());
        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO)session.getAttribute("user");
        commentDTO.setAuthor(userDTO.getUserNum());
        commentDAO.MakeComment(commentDTO);
        return "success";
    }
}
