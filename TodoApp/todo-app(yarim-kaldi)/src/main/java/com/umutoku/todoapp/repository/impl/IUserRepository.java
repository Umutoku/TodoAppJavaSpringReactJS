package com.umutoku.todoapp.repository.impl;

import com.umutoku.todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);

    Optional<User> findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findTopByEmail(String email);

    User findTopByPassword(String password);



}
