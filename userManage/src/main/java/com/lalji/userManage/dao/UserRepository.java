package com.lalji.userManage.dao;

import com.lalji.userManage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
