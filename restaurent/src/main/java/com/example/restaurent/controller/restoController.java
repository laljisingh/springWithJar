package com.example.restaurent.controller;


import com.example.restaurent.model.restaurent;
import com.example.restaurent.service.restoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resto-app")
public class restoController {

    @Autowired
    private restoService restoService;


    //http://localhost:8080/api/resto-app/add-resto
    @PostMapping("/add-resto")
    public void addTodo(@RequestBody restaurent resto) {
        restoService.addTodo(resto);
    }

    @GetMapping("/find-resto/name/{name}")
    public restaurent findTodoById(@PathVariable String name) {
        return restoService.findByName(name);
    }

    @GetMapping("/find-all")
    public List<restaurent> findAllTodos() { // controller is talking to the service layer
        return restoService.findAll();
    }


}