package com.lalji.userManage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "User_table")
public class User {
    @Id
    @Column(name = "User_id")
    @NotNull(message = "Please enter user id")
    private String userId;
    @Column(name = "User_Name")
    @NotNull(message = "Please enter proper employee name")
    @Size(min=5, message = "Name should be atleast 5 characters")
    @Size(max=12, message = "Name should not be greater than 12 characters")
    private String username;



    @NotNull(message = "Please provide a date of birth format yyyy-MM-dd.")
    private String dateOfBirth;

    @Email(message = "Please enter a valid email Id", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
    @NotBlank(message = "Please enter proper employee email address")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Please enter proper employee phone number")
    @Size(max=12, message = "Name should not be greater than 12 characters")
    @Column(name = "Phone_Number")
    private String phone_Number;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please provide a date yyyy-MM-dd format.")
    @Column(name = "date")
    private String date;

    @NotNull(message = "Not blank Please enter time")
    @Column(name = "time")
    private String time;


}
