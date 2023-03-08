package com.laljisingh.chatapplicatio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_chat_history")
public class ChatHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_Id")
    private Integer chatId;
    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private Users to;
    @JoinColumn(name = "from_user_id")
    @ManyToOne
    private Users from;
    @Column(name = "message")
    private String message;
    @Column(name="created_date")
//    @CreationTimestamp
    private Timestamp createdDate;
    @Column(name="updated_date")
//    @UpdateTimestamp
    private Timestamp updatedDate;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status statusId;
}
