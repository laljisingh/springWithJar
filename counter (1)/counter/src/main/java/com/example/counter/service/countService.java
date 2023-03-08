package com.example.counter.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class countService {
    public static int count = 0;

    public void setCount(){
        count++;
    }
    public int getCount(){
        return count;
    }
    public static HashMap<String, Integer> mp=new HashMap<>();
    public void countName(String name){
        mp.put(name, mp.getOrDefault(name, 0)+1);
    }

    @Override
    public String toString() {
        return "countService{}";
    }

    public String countName(){
        return "Checking";
    }


}
