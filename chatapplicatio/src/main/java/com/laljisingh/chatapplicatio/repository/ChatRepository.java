package com.laljisingh.chatapplicatio.repository;

import com.laljisingh.chatapplicatio.model.ChatHistory;
import com.laljisingh.chatapplicatio.model.Users;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatHistory,Integer> {
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

    @Query(value = "SELECT * FROM chatapplication_db.tbl_chat_history where from_user_id = :senderId and status_id = 1;", nativeQuery = true)
    List<ChatHistory> getMessageByuserId(int senderId);


    @Query(value = "select * from tbl_chat_history where (from_user_id = :senderId and to_user_id = :receiverId) or (to_user_id = :receiverId and from_user_id= :senderId) and status_id = 1 order by created_date;", nativeQuery = true)
    List<ChatHistory> getConversation(int senderId, int receiverId);
}
