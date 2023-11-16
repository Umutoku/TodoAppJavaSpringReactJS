package com.umutoku.todo.repositories;

import com.umutoku.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "todos", path = "todos")
@RestResource(exported = false)
public interface TodoRepository extends JpaRepository<Todo, Integer > {
    @Query( value = "SELECT * FROM TODO",
            nativeQuery = true)
    List<Todo> findAll();
}
