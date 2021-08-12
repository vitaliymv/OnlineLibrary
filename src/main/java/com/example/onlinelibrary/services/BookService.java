package com.example.onlinelibrary.services;

import com.example.onlinelibrary.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    void create(String name,
                String lastName,
                String title,
                String description,
                MultipartFile file) throws IOException;


    Book update(Book book);


    void deleteById(Long id);


    Optional<Book> findById(Integer id);


    List<Book> findByAuthor(String name);


    List<Book> getAllBooks();

    Book getBook(Long id);

    Book getBookByName(String title);
}
