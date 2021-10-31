package com.dsi.spring.controller;

import com.dsi.spring.config.FileUploadUtil;
import com.dsi.spring.dao.RoleDao;
import com.dsi.spring.dao.UserDao;
import com.dsi.spring.model.Role;
import com.dsi.spring.model.User;
import com.dsi.spring.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
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
        // default role set as user
        Role role = roleDao.findById(1).orElse(new Role()); // user role fetched
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);
        user.setProfilePicPath("/images/profile/default.png");

        user.setEnabled(true);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));  // password encrypted
        User savedUser = userDao.save(user);
        return "login";
    }

    @RequestMapping("/all_user")
    public String allUser(Model model) {
        List<User> users = userDao.findAll();
        model.addAttribute("allUser", users);
        return "all_user";
    }

    @RequestMapping("/delete/user/{id}")
    public String deleteUser( @PathVariable(value="id") Long userId ) {
        userDao.deleteById(userId);
        return "redirect:/all_user";
    }

    @RequestMapping("/user_profile")
    public String profile( @AuthenticationPrincipal MyUserDetails principal, Model model ) {
        User user = userDao.findById(principal.getId()).orElse( new User());
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping("/edit/user/{id}")
    public String editUserPage( @PathVariable(value="id") Long userId , Model model ) {
        User user = userDao.findById(userId).orElse( new User());
        model.addAttribute("user", user);
        model.addAttribute("allRole" , roleDao.findAll());
        return "edit_user";
    }

    @RequestMapping("/edit/user/{id}/submit")
    public String editUserSubmit( @PathVariable(value="id") Long userId , User userDetails ) {
        System.out.println(userDetails);
        User user = userDao.findById(userId).orElse( new User());
        user.setUsername(userDetails.getUsername());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setRoles(userDetails.getRoles());
        userDao.save(user);
        return "redirect:/all_user";
    }


    @RequestMapping("/edit/user/profile/image/{id}")
    public String uploadSolution( @PathVariable(value="id") Long id, @RequestParam("file") MultipartFile file ) {

        try {
            String uploadDir = "src/main/resources/static/images/profile";
            String profilePicPath = "images/profile/" + FileUploadUtil.saveFile(id, uploadDir, file);
            System.out.println("File Uploaded Successfully : " + profilePicPath);

            User user = userDao.findById(id).orElse(new User());
            user.setProfilePicPath(profilePicPath);
            userDao.save(user);

        } catch (Exception e) {
            System.out.println("Excepton : " + e + " has occured.");
        }
        return "redirect:/user_profile";
    }



}
