package com.laljisingh.hibernateMapping.repository;

import com.laljisingh.hibernateMapping.model.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Integer> {
}
