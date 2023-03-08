package com.example.restaurent.service;

import com.example.restaurent.model.restaurent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class restoService {

    private static List<restaurent> restoList=new ArrayList<>();

    static {
        restoList.add(new restaurent("TajResto","Allahabad India",1234567890,"GoodServiess",25));
        restoList.add(new restaurent("AgraResto","uttar Pradesh India",012563322,"GoodServiess",25));
    }

    public List<restaurent> findAll() { // Business Logic
        return restoList;
    }

    public restaurent findByName(String name) { // business LOgic
        restaurent restaurent;
        for(int i = 0; i<restoList.size(); i++){
            if(restoList.get(i).equals(name)){
                return null;
            }
        }
        return null;
    }

    public void addTodo(restaurent resto) {
        restoList.add(resto);
    }


}
