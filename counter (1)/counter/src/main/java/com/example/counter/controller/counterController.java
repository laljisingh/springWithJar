package com.example.counter.controller;

import com.example.counter.service.countService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping("/count")
public class counterController {

    @Autowired
    countService count;




    @GetMapping("/count")
    public int count(){
        count.setCount();

        return count.getCount();
    }



    @GetMapping("/counts/{name}")
    public HashMap<String,Integer> counts(@PathVariable String name){
        count.countName(name);
        return count.mp;
    }


}
