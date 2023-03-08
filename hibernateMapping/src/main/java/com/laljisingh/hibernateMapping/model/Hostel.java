package com.laljisingh.hibernateMapping.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_Hostel")
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer hostel_id;
    private String name;
    private String type;
    private Integer roomNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Student student;
}
