package com.umutoku.todoapp.controller;

import com.umutoku.todoapp.model.Todo;
import com.umutoku.todoapp.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;
    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    //POST
    @PostMapping("/addTodo")
    public Todo addTodo(@RequestBody Todo todo) {
        logger.info("Todo object {}", todo.toString());
        return todoService.saveTodo(todo);
    }

    @PostMapping("/addTodos")
    public List<Todo> addTodos(@RequestBody List<Todo> todos) {
        return todoService.saveTodos(todos);
    }

    //GET
    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoService.getTodos();
    }

    @GetMapping("/todoById/{id}")
    public Todo findTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @GetMapping("/todoByName/{name}")
    public Todo findTodoByName(@PathVariable String name) {
        return todoService.getTodoByName(name);
    }

    @GetMapping("/listTodoByUsername/{username}")
    public List<Todo> findTodosByUsername(@PathVariable String username) {
        return todoService.getTodosForUser(username);
    }

    //PUT
    @PutMapping("/update")
    public Todo updateTodo(@RequestBody Todo todo) {
        System.out.println("UPDATED");
        return todoService.updateTodo(todo);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }

}
