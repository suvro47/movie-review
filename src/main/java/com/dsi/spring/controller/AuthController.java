package com.dsi.spring.controller;

import com.dsi.spring.model.User;
import com.dsi.spring.security.MyUserDetails;
import com.dsi.spring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/logout-success")
    public String logoutSuccess() {
        return "login";
    }

    @RequestMapping("/signup")
    public String signupPage(Model model) {
        return authService.signupPage(model);
    }

    @RequestMapping("/signup-submit")
    public String signup_submit(User user) {
        return authService.signup_submit(user);
    }

    @RequestMapping("/all_user")
    public String allUser(Model model) {
        return authService.allUser(model);
    }

    @RequestMapping("/delete/user/{id}")
    public String deleteUser( @PathVariable(value="id") Long userId ) {
        return authService.deleteUser(userId);
    }

    @RequestMapping("/user_profile")
    public String profile( @AuthenticationPrincipal MyUserDetails principal, Model model ) {
        return authService.profile(principal, model);
    }

    @RequestMapping("/edit/user/{id}")
    public String editUserPage( @PathVariable(value="id") Long userId , Model model ) {
        return authService.editUserPage(userId, model);
    }

    @RequestMapping("/edit/user/{id}/submit")
    public String editUserSubmit( @PathVariable(value="id") Long userId , User userDetails ) {
       return authService.editUserSubmit(userId, userDetails);
    }



}
