package com.umutoku.todoapp.service.impl;

import com.umutoku.todoapp.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
    User getUser(User user);
    UserDetails getUserByUsername(String username);
    boolean getUserByUsername(String username, String password);
    boolean findUserByUsername(String username);
    void saveUser(User user);
    User createUser(User user);
}

