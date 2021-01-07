package com.jandy.grasszone.controller;

import com.jandy.grasszone.dao.PostDAO;
import com.jandy.grasszone.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class PostRestController {
    @Autowired
    PostDAO postDAO;

    @GetMapping(value = "/post/page/{board}/{pageNum}")
    public ModelAndView GetPostsByBoardAndPageNumber(@PathVariable("board") int board,
                                                     @PathVariable("pageNum") int pageNum,
                                                     @RequestParam("postCountInPage") int postCountInPage) throws Exception
    {
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

    @GetMapping(value = "/post/amount")
    public String GetPostAmountWithBoard(@RequestParam("boardID") int boardID) throws Exception {
        int postAmount = 0;
        if(boardID == 0) {
            postAmount = postDAO.GetPostAmount();
        }
        else {
            postAmount = postDAO.GetPostAmountWithBoard(boardID);
        }
        return Integer.toString(postAmount);
    }
}
