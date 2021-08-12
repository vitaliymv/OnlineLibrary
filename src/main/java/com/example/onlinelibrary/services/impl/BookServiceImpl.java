package com.example.onlinelibrary.services.impl;

import com.example.onlinelibrary.entity.Author;
import com.example.onlinelibrary.entity.Book;
import com.example.onlinelibrary.repository.AuthorRepository;
import com.example.onlinelibrary.repository.BookRepository;
import com.example.onlinelibrary.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public void create(String name,
                       String lastName,
                       String title,
                       String description,
                       MultipartFile file) throws IOException {
        Book book = new Book();
        Author author = new Author();

        book.setTitle(title);
        book.setDescription(description);
        book.setFile(file.getBytes());
        book.setFileName(file.getOriginalFilename());

        author.setName(name);
        author.setLastName(lastName);

        author.getBooks().add(book);
        book.getAuthors().add(author);

        authorRepository.saveAndFlush(author);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return bookRepository.findById(Long.valueOf(id));
    }

    @Override
    public List<Book> findByAuthor(String name) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public Book getBookByName(String title) {
        return bookRepository.findBookByTitle(title);
    }


}
