package com.laljisingh.test2602.service;


import com.laljisingh.test2602.model.Book;
import com.laljisingh.test2602.repository.BookRepository;
import com.laljisingh.test2602.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    StudentRepository studentRepository;
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public String deleteBook(String bookId) {
        for (Book book : bookRepository.findAll()) {
            if (book.getBook_Id()==(Integer.valueOf(bookId))){
                bookRepository.delete(book);
                return "Deleted";
            }
        }
        return "Not found";
    }


    public String updateBook(Book newbook, String bookid) {

        for (Book book : bookRepository.findAll()) {
            if (book.getBook_Id()==(Integer.valueOf(bookid))){
                book.setDescription(newbook.getDescription());
                book.setAuthor(newbook.getAuthor());
                book.setTitle(newbook.getTitle());
                book.setPrice(newbook.getPrice());
                bookRepository.save(book);
                return "Updated";
            }
        }
        return "Not found";
    }
}
