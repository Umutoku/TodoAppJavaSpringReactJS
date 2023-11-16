package com.umutoku.todoapp.repository.impl;

import com.umutoku.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ITodoRepository extends JpaRepository<Todo, Long> {
    Todo findByName(String name);

    @Override
    List<Todo> findAll();

    List<Todo> findAllByEmail(String email);
}
