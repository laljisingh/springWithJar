package com.laljisingh.chatapplicatio.repository;

import com.laljisingh.chatapplicatio.model.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query(value = "select * from tbl_user where name = :usr and status_id = 1", nativeQuery = true)
    List<Users> findByusername(String usr);


    @Query(value = "select * from tbl_user where status_id = 1", nativeQuery = true)
    public List<Users> getAllUsers();


    @Query(value = "select * from tbl_user where user_id = :userId and status_id = 1", nativeQuery = true)
    List<Users> getUserByUserId(Integer userId);



    @Modifying
    @Transactional
    @Query(value = "update  tbl_user set status_id = 0 where user_id = :user_id",
            countQuery = "select count(*) from tbl_user", nativeQuery = true)
    void updateUserByUserId(Integer user_id);

//    @Query(value = "update  tbl_user set status_id = 0 where user_id = :user_id", nativeQuery = true)
//    void updateUserByUserI
}
