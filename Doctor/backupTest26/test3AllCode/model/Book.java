package com.test3.test3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.boot.registry.selector.spi.StrategyCreator;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer book_Id;

    private String title;
    private String author;
    private String description;
    private String price;

    @ManyToOne
    private Student student;
}
