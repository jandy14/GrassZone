package com.jandy.grasszone.controller;

import com.jandy.grasszone.dao.BoardDAO;
import com.jandy.grasszone.dao.PostDAO;
import com.jandy.grasszone.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BoardController {
    @Autowired
    private BoardDAO boardDAO;
    @Autowired
    private PostDAO postDAO;

    @GetMapping(value = "/board")
    public String BoardMain(BoardDTO boardDTO, Model model, HttpServletRequest req) throws Exception {
        System.out.println(boardDTO.toString());
        if(boardDTO.getBoardID() == 0) {
            model.addAttribute("posts", postDAO.GetAllPosts());
        }
        else {
            model.addAttribute("posts", postDAO.GetPostsWithBoard(boardDTO));
        }
        model.addAttribute("boards", boardDAO.GetAllBoard());
        return "board.html";
    }
}
