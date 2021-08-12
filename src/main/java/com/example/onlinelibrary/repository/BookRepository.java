package com.example.onlinelibrary.repository;

import com.example.onlinelibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookById(Long id);
    Book findBookByTitle(String title);
}
