package com.laljisingh.hibernateMapping.repository;

import com.laljisingh.hibernateMapping.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
