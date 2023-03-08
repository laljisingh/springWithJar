package com.laljisingh.hibernateMapping.service;

import com.laljisingh.hibernateMapping.model.Hostel;
import com.laljisingh.hibernateMapping.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostelService {
    @Autowired
    HostelRepository hostelRepository;
    public void addHostel(Hostel hostel) {
        hostelRepository.save(hostel);
    }
}
