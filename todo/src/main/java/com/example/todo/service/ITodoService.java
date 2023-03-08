package com.example.todo.service;

import com.example.todo.model.Todo;

import java.util.List;

public interface ITodoService {
    public List<Todo> findAll();

    public Todo findById(int id);

    public int addTodo(Todo todo);

    public String deleteTodo(int id);

    public void updateTodo(int id,Todo newTodo);
}
