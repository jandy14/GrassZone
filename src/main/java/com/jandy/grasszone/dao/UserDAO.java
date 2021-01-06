package com.jandy.grasszone.dao;

import com.jandy.grasszone.dto.UserDTO;
import java.util.List;

public interface UserDAO {
    List<UserDTO> selectUsers(UserDTO param) throws Exception;
    void CreateUser(UserDTO param) throws Exception;
    List<UserDTO> FindUserWithID(UserDTO param) throws Exception;
    List<UserDTO> SignIn(UserDTO param) throws Exception;
}
