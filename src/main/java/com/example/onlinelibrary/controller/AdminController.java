package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.services.BookService;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AdminController {

    private BookService bookService;

    public AdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @SneakyThrows
    @PostMapping("/admin/create")
    public ResponseEntity addBook(@RequestParam("name") String name,
                                  @RequestParam("lastName") String lastName,
                                  @RequestParam("title") String title,
                                  @RequestParam("description") String description,
                                  @RequestParam("file") MultipartFile file) {
        bookService.create(name, lastName, title, description, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/delete")
    public HttpStatus remove(@RequestParam long id) {
        bookService.deleteById(id);
        return HttpStatus.ACCEPTED;
    }

}
