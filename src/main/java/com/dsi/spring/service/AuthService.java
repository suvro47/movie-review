package com.dsi.spring.service;

import com.dsi.spring.exception.ResourceAlreadyExists;
import com.dsi.spring.exception.ResourceNotFoundException;
import com.dsi.spring.model.User;
import com.dsi.spring.security.MyUserDetails;
import org.springframework.ui.Model;

import java.util.List;

public interface AuthService {

    void signupSubmit(User user) throws ResourceAlreadyExists;

    List<User> allUser() throws ResourceNotFoundException;

    void deleteUser(Long userId) throws ResourceNotFoundException;

    User profile(MyUserDetails principal);

    User setEditUserPage(Long userId) throws ResourceNotFoundException;

    void editUserSubmit( Long userId , User userDetails);

}
