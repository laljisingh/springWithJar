package com.laljisingh.hibernateMapping.controller;

import com.laljisingh.hibernateMapping.model.Hostel;
import com.laljisingh.hibernateMapping.model.Student;
import com.laljisingh.hibernateMapping.repository.StudentRepository;
import com.laljisingh.hibernateMapping.service.HostelService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HostelController {
    @Autowired
    HostelService hostelService;

    @Autowired
    StudentRepository studentRepository;
    @PostMapping("/add-hostel")
    public String addHostel(@RequestBody String newHostel){
        JSONObject json = new JSONObject(newHostel);
        Hostel hostel= setHostel(json);
        hostelService.addHostel(hostel);
        return newHostel;
    }

    private Hostel setHostel(JSONObject json) {
        Hostel hostel=new Hostel();
        hostel.setHostel_id(json.getInt("hostel_Id"));
        hostel.setType(json.getString("type"));
        hostel.setName(json.getString("name"));
        hostel.setRoomNumber(json.getInt("roomNumber"));

        String student_Id = String.valueOf(json.getString("studentId"));
//        Student student = studentRepository.findById(Integer.valueOf(student_Id)).get();
        hostel.setStudent(null);

        return hostel;
    }
}
