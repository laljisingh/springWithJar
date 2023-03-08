package com.laljisingh.chatapplicatio.service;

import com.laljisingh.chatapplicatio.model.Status;
import com.laljisingh.chatapplicatio.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    StatusRepository repository;

    public int saveStatus(Status status) {
        Status statusObj = repository.save(status);
        return statusObj.getStatusId();
    }


//    public int saveStatus(Status status) {
//        Status statusObj = repository.save(status);
//        return statusObj.getStatusId();
//    }
}
