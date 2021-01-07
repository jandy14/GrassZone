package com.jandy.grasszone.dao;

import com.jandy.grasszone.dto.BoardDTO;
import com.jandy.grasszone.dto.PostDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostDAO {
    void MakePost(PostDTO param) throws Exception;
    List<PostDTO> GetPostsWithBoard(BoardDTO param) throws Exception;
    List<PostDTO> GetAllPosts() throws Exception;
    List<PostDTO> GetPostsByBoardAndPageNum(@Param("boardID") int boardID,
                                            @Param("startIndex") int startIndex,
                                            @Param("postCount") int postCount) throws Exception;
    List<PostDTO> GetPostsByPageNum(@Param("startIndex") int startIndex,
                                    @Param("postCount") int postCount) throws Exception;
    PostDTO GetPostWithPostID(int postID) throws Exception;
    void IncreaseViews(int postID) throws Exception;
    int GetPostAmountWithBoard(int boardID) throws Exception;
    int GetPostAmount() throws Exception;


}
