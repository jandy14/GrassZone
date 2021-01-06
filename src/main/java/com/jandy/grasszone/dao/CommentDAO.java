package com.jandy.grasszone.dao;

import com.jandy.grasszone.dto.CommentDTO;

import java.util.List;

public interface CommentDAO {
    List<CommentDTO> GetPostComments(int postID) throws Exception;
    void MakeComment(CommentDTO commentDTO) throws Exception;
}
