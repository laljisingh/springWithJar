package com.laljisingh.hibernateMapping.service;

import com.laljisingh.hibernateMapping.model.Book;
import com.laljisingh.hibernateMapping.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public void addBook(Book book) {
        bookRepository.save(book);
    }
}
