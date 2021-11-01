package com.dsi.spring.service.impl;

import com.dsi.spring.dao.RoleDao;
import com.dsi.spring.dao.UserDao;
import com.dsi.spring.model.Role;
import com.dsi.spring.model.User;
import com.dsi.spring.security.MyUserDetails;
import com.dsi.spring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public String signupPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "home/signup";
    }

    @Override
    public String signup_submit(User user) {
        // default role set as user
        Role role = roleDao.findById(1).orElse(new Role()); // user role fetched
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);
        user.setProfilePicPath("/images/profile/default.png");

        user.setEnabled(true);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));  // password encrypted
        User savedUser = userDao.save(user);
        return "home/login";
    }

    @Override
    public String allUser(Model model) {
        List<User> users = userDao.findAllUser();
        model.addAttribute("allUser", users);
        return "home/all_user";
    }

    @Override
    public String deleteUser(Long userId) {
        userDao.deleteById(userId);
        return "redirect:/home/all_user";
    }

    @Override
    public String profile(MyUserDetails principal, Model model) {
        User user = userDao.findById(principal.getId()).orElse( new User());
        model.addAttribute("user", user);
        return "home/profile";
    }

    @Override
    public String editUserPage(Long userId, Model model) {
        User user = userDao.findById(userId).orElse( new User());
        model.addAttribute("user", user);
        model.addAttribute("allRole" , roleDao.findAll());
        return "home/edit_user";
    }

    @Override
    public String editUserSubmit(Long userId, User userDetails) {
        User user = userDao.findById(userId).orElse( new User());
        user.setUsername(userDetails.getUsername());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setRoles(userDetails.getRoles());
        userDao.save(user);
        return "redirect:/home/all_user";
    }


}
