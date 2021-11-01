package com.dsi.spring.service;

import com.dsi.spring.model.User;
import com.dsi.spring.security.MyUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface AuthService {

    public String signupPage(Model model);

    public String signup_submit(User user);

    public String allUser(Model model);

    public String deleteUser(Long userId);

    public String profile(MyUserDetails principal, Model model );

    public String editUserPage(Long userId , Model model);

    public String editUserSubmit( Long userId , User userDetails);

}
