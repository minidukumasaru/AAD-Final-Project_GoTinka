package org.example.gotinka_travelling.Service;


import org.example.gotinka_travelling.dto.UserDTO;
import org.example.gotinka_travelling.entity.User;

import java.util.List;


public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);

    List<UserDTO> getAllUsers();
    int deleteUser(String email);
    Long getUserCountByRoleUser();
    // New method to fetch current logged-in user
    User getCurrentUser();
}