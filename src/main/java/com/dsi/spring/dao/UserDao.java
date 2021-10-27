package com.dsi.spring.dao;

import com.dsi.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

}
