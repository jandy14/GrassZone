package com.jandy.grasszone.dao;

import com.jandy.grasszone.dto.BoardDTO;
import com.jandy.grasszone.dto.PostDTO;

import java.util.List;

public interface PostDAO {
    void MakePost(PostDTO param) throws Exception;
    List<PostDTO> GetPostsWithBoard(BoardDTO param) throws Exception;
    List<PostDTO> GetAllPosts() throws Exception;
    PostDTO GetPostWithPostID(int postID) throws Exception;
    void IncreaseViews(int postID) throws Exception;
}
