package com.test3.test3.service;


import com.test3.test3.model.Book;
import com.test3.test3.model.Course;
import com.test3.test3.model.Laptop;
import com.test3.test3.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopService {
    @Autowired
    LaptopRepository laptopRepository;

    public void addLaptop(Laptop laptop) {
        laptopRepository.save(laptop);
    }

    public List<Laptop> getAllLaptop() {
        return laptopRepository.findAll();
    }

    public String deleteBook(String laptopId) {
        for (Laptop laptop : laptopRepository.findAll()) {
            if (laptop.getLaptop_id()==(Integer.valueOf(laptopId))){
                laptopRepository.delete(laptop);
                return "Deleted";
            }
        }
        return "Not found";
    }

}
