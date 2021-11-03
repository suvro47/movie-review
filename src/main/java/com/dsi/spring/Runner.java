package com.dsi.spring;

import com.dsi.spring.dao.RoleDao;
import com.dsi.spring.dao.UserDao;
import com.dsi.spring.model.Role;
import com.dsi.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public void run(String... args) throws Exception {

        if( roleDao.findAll().isEmpty() ) {

            // USER ROLE
            Role role_user = new Role();
            role_user.setName("USER");
            roleDao.save(role_user);

            // CREATOR ROLE
            Role role_creator = new Role();
            role_creator.setName("CREATOR");
            roleDao.save(role_creator);

            // EDITOR ROLE
            Role role_editor = new Role();
            role_editor.setName("EDITOR");
            roleDao.save(role_editor);

            // ADMIN ROLE
            Role role_admin = new Role();
            role_admin.setName("ADMIN");
            roleDao.save(role_admin);

            // dummy user added
            User user = new User();
            user.setUsername("user");
            user.setEnabled(true);
            user.setPassword(new BCryptPasswordEncoder().encode("user"));

            Role role = roleDao.findById(1).orElse(new Role()); // user role fetched
            Set<Role> roles = new HashSet<Role>();
            roles.add(role);

            user.setRoles(roles);
            userDao.save(user);


            // dummy creator added
            User creator = new User();
            creator.setUsername("creator");
            creator.setEnabled(true);
            creator.setPassword(new BCryptPasswordEncoder().encode("creator"));

            role = roleDao.findById(2).orElse(new Role()); // creator role fetched
            roles = new HashSet<Role>();
            roles.add(role);

            creator.setRoles(roles);
            userDao.save(creator);

            // dummy creator added
            User editor = new User();
            editor.setUsername("editor");
            editor.setEnabled(true);
            editor.setPassword(new BCryptPasswordEncoder().encode("editor"));

            role = roleDao.findById(3).orElse(new Role()); // editor role fetched
            roles = new HashSet<Role>();
            roles.add(role);

            editor.setRoles(roles);
            userDao.save(editor);

            // dummy admin added
            User admin = new User();
            admin.setUsername("admin");
            admin.setEnabled(true);
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));

            role = roleDao.findById(4).orElse(new Role()); // admin role fetched
            roles = new HashSet<Role>();
            roles.add(role);

            admin.setRoles(roles);
            userDao.save(admin);

        }

    }
}
