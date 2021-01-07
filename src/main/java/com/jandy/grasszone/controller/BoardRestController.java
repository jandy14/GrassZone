package com.jandy.grasszone.controller;

import com.jandy.grasszone.dao.PostDAO;
import com.jandy.grasszone.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class BoardRestController {
    @Autowired
    PostDAO postDAO;


}
