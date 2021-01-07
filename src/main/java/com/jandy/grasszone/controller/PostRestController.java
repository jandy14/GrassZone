package com.jandy.grasszone.controller;

import com.jandy.grasszone.dao.PostDAO;
import com.jandy.grasszone.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class PostRestController {
    @Autowired
    PostDAO postDAO;

    private int postCountInPage = 5;

    @GetMapping(value = "/post/page/{board}/{pageNum}")
    public ModelAndView GetPostsByBoardAndPageNumber(@PathVariable("board") int board, @PathVariable("pageNum") int pageNum ) throws Exception{
        List<PostDTO> posts;
        if(board == 0) {
            posts = postDAO.GetPostsByPageNum((pageNum-1) * postCountInPage, postCountInPage);
        }
        else {
            posts = postDAO.GetPostsByBoardAndPageNum(board, (pageNum-1) * postCountInPage, postCountInPage);
        }
        ModelAndView modelAndView = new ModelAndView("postsinboard.html");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }
}
