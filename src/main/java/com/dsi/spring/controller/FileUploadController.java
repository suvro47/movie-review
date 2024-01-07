package com.dsi.spring.controller;

import com.dsi.spring.config.FileUploadUtil;
import com.dsi.spring.dao.UserDao;
import com.dsi.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/edit/user/profile/image/{id}")
    public String profilePictureUpdate(@PathVariable(value="id") Long id, @RequestParam("file") MultipartFile file ) {

        try {
            User user = userDao.findById(id).orElse(new User());
            String uploadDir = "src/main/resources/static/images/profile/"+ user.getUsername()+ "_" + id.toString() + "/";
            String profilePicPath = "images/profile/"+ user.getUsername()+ "_" + id.toString()+ "/" + FileUploadUtil.saveFile(id, uploadDir, file);
            System.out.println("File Uploaded Successfully : " + profilePicPath);

            user.setProfilePicPath(profilePicPath);
            userDao.save(user);

        } catch (Exception e) {
            System.out.println("Excepton : " + e + " has occured.");
        }
        return "redirect:/user_profile";
    }
}
