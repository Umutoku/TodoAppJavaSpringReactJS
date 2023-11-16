//package com.umutoku.todoapp.service;
//
//import com.umutoku.todoapp.enums.Role;
//import com.umutoku.todoapp.model.User;
//import com.umutoku.todoapp.repository.impl.IUserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import java.util.ArrayList;
//import java.util.List;
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    private final IUserRepository userRepository;
//    public CustomUserDetailsService(IUserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findUserByEmail(email);
//        List<String> roles = new ArrayList<>();
//        roles.add(String.valueOf(Role.USER));
//        UserDetails userDetails =
//                org.springframework.security.core.userdetails.User.builder()
//                        .username(user.getEmail())
//                        .password(user.getPassword())
//                        .roles(roles.toArray(new String[0]))
//                        .build();
//        return userDetails;
//    }
//
//}
