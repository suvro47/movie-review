package com.dsi.spring.controller;

import com.dsi.spring.dao.RoleDao;
import com.dsi.spring.dao.UserDao;
import com.dsi.spring.model.Role;
import com.dsi.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

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
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping("/signup-submit")
    public String signup_submit(User user) {

        System.out.println("I am here");

        // default role set ad admin
        Role role = roleDao.findById(4).orElse(new Role()); // admin role fetched
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);

        user.setEnabled(true);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));  // password encrypted
        User savedUser = userDao.save(user);


        System.out.println(savedUser);

        return "login";
    }




}
