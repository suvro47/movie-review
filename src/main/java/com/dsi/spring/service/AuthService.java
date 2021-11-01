package com.dsi.spring.service;

import com.dsi.spring.model.User;
import com.dsi.spring.security.MyUserDetails;
import org.springframework.ui.Model;

public interface AuthService {

    String signupPage(Model model);

    String signup_submit(User user);

    String allUser(Model model);

    String deleteUser(Long userId);

    String profile(MyUserDetails principal, Model model );

    String editUserPage(Long userId , Model model);

    String editUserSubmit( Long userId , User userDetails);

}
