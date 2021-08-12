package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.entity.Book;
import com.example.onlinelibrary.services.BookService;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MainController {

    private BookService bookService;

    public MainController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }

    @SneakyThrows
    @GetMapping("/user/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam Long id) {
        Book bookOptional = bookService.getBook(id);
        FileOutputStream fos = new FileOutputStream(bookOptional.getFileName());
        fos.write(bookOptional.getFile());
        fos.flush();
        fos.close();

        Path path = Paths.get(bookOptional.getFileName());
        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);
        File f = new File(bookOptional.getFileName());
        f.delete();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentLength(data.length)
                .body(resource);
    }

    @GetMapping("/user/search")
    public String getBookByName(@RequestParam String title, Model model) {
        model.addAttribute("books", bookService.getBookByName(title));
        return "index";
    }
}
