package com.laljisingh.chatapplicatio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="tbl_user")
public class Users {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name="name")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name="phoneNumber")
    private String phoneNumber;
    @Column(name="gender")
    private String gender;
    @Column(name="firstName")
    private String fistName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="age")
    private Integer age;
    @Column(name="created_date")
    @CreationTimestamp
    private Timestamp createdDate;
    @Column(name="updated_date")
    @UpdateTimestamp
    private Timestamp updatedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status statusId;

}
