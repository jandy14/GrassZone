package com.jandy.grasszone.dao;

import com.jandy.grasszone.dto.BoardDTO;

import java.util.List;

public interface BoardDAO {
    List<BoardDTO> GetAllBoard() throws Exception;
}
