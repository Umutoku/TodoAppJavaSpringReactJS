package com.umutoku.todoapp.service;

import com.umutoku.todoapp.model.User;
import com.umutoku.todoapp.repository.impl.IUserRepository;
import com.umutoku.todoapp.service.impl.IUserService;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        userRepository = userRepository;
    }

    public User getUser(User user) {
        System.out.println("Service GET *****");
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }


    @Override
    public UserDetails getUserByUsername(String email) {
        return null;
    }

    public boolean getUserByUsername(String username, String password) {
        boolean username_present;
        boolean password_present;
        try {
            username_present = userRepository.findTopByEmail(username) != null ? true : false;
            System.out.println("Username present: " + username_present);
            password_present = userRepository.findTopByPassword(password) != null ? true : false;
            System.out.println("Password present: " + password_present);
        } catch (NonUniqueResultException nre) {
            return true;
        }
        return username_present && password_present;
    }

    public boolean findUserByUsername(String email) {
        boolean username_present;
        try {
            username_present = userRepository.findTopByEmail(email) != null ? true : false;
            System.out.println("Username present (U): " + username_present);
        } catch (NonUniqueResultException nre) {
            return true;
        }
        return username_present;
    }


    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    public User createUser(User user){
        User newUser = userRepository.save(user);
        userRepository.flush();
        return newUser;
    }

}
