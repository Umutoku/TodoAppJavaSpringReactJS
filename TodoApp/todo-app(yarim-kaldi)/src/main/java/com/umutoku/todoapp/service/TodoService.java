package com.umutoku.todoapp.service;

import com.umutoku.todoapp.model.Todo;
import com.umutoku.todoapp.repository.impl.ITodoRepository;
import com.umutoku.todoapp.service.impl.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements ITodoService {
    @Autowired
    private ITodoRepository ITodoRepository;

    public TodoService(ITodoRepository ITodoRepository) {
        this.ITodoRepository = ITodoRepository;
    }

    //POST
    public Todo saveTodo(Todo todo) {
        System.out.println(todo.toString());
        return ITodoRepository.save(todo);
    }

    //Optional!
    public List<Todo> saveTodos(List<Todo> todos) {
        return ITodoRepository.saveAll(todos);
    }

    //GET
    public List<Todo> getTodos() {
        return ITodoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return ITodoRepository.findById(id).orElse(null);
    }

    public Todo getTodoByName(String name)
    {
        return ITodoRepository.findByName(name);
    }

    public List<Todo> getTodosForUser(String email) {
        return ITodoRepository.findAllByEmail(email);
    }

    //PUT
    public Todo updateTodo(Todo todo) {
        System.out.println("updates");
        Todo existing_todo = ITodoRepository.findById(todo.getId()).orElse(null);
        existing_todo.setName(todo.getName());
        existing_todo.setDescription(todo.getDescription());
        existing_todo.setCompleted(todo.isCompleted());
        return ITodoRepository.save(existing_todo);
    }

    //DELETE
    public String deleteTodo(Long id) {
        ITodoRepository.deleteById(id);
        return id + " id -> todo removed/completed";
    }
}