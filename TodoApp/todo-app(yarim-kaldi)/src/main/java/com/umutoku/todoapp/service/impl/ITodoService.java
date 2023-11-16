package com.umutoku.todoapp.service.impl;

import com.umutoku.todoapp.model.Todo;

import java.util.List;

public interface ITodoService {

    // Method to save a single Todo
    Todo saveTodo(Todo todo);

    // Method to save multiple Todos
    List<Todo> saveTodos(List<Todo> todos);

    // Method to retrieve all Todos
    List<Todo> getTodos();

    // Method to retrieve a single Todo by its ID
    Todo getTodoById(Long id);

    // Method to retrieve a Todo by its name
    Todo getTodoByName(String name);

    // Method to retrieve Todos for a specific user
    List<Todo> getTodosForUser(String email);

    // Method to update a Todo
    Todo updateTodo(Todo todo);

    // Method to delete a Todo by its ID
    String deleteTodo(Long id);
}
