package com.laljisingh.chatapplicatio.repository;

import com.laljisingh.chatapplicatio.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
