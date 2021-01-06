package com.jandy.grasszone.controller;

import com.jandy.grasszone.dao.CommentDAO;
import com.jandy.grasszone.dto.CommentDTO;
import com.jandy.grasszone.dto.UserDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentDAO commentDAO;

    @GetMapping("/comments/{postID}")
    @ResponseBody
    public List<CommentDTO> GetPostComments(@PathVariable("postID") int postID ) throws Exception {
        List<CommentDTO> comments = commentDAO.GetPostComments(postID);
//        System.out.println(comments);
        return comments;
    }
    @PostMapping("/submitcomment")
    public String MakeComment(CommentDTO commentDTO, HttpServletRequest req) throws Exception {
//        System.out.println(commentDTO.toString());
        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO)session.getAttribute("user");
        if(ControllerUtil.IsSignedIn(req) != true)
            return "fail signin";
        commentDTO.setAuthor(userDTO.getUserNum());
        commentDAO.MakeComment(commentDTO);
        return "success";
    }
}
