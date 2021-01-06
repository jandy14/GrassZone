package com.jandy.grasszone.controller;

import com.jandy.grasszone.dao.BoardDAO;
import com.jandy.grasszone.dao.PostDAO;
import com.jandy.grasszone.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PostController {
    @Autowired
    private BoardDAO boardDAO;
    @Autowired
    private PostDAO postDAO;

    @GetMapping("/writepost")
    public String WritePost(Model model, HttpServletRequest req) throws Exception {
        model.addAttribute("boards", boardDAO.GetAllBoard());
        return "writepost.html";
    }
    @GetMapping("/post/{postID}/before")
    public String BeforeViewPost(Model model, @PathVariable("postID") int postID, HttpServletRequest req) throws Exception {
        System.out.println(postID);
        postDAO.IncreaseViews(postID);
        return "redirect:/post/"+postID;
    }
    @GetMapping("/post/{postID}")
    public String ViewPost(Model model, @PathVariable("postID") int postID, HttpServletRequest req) throws Exception {
        PostDTO postDTO = postDAO.GetPostWithPostID(postID);
        model.addAttribute("post", postDTO);
        return "post.html";
    }
}
