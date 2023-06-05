package com.example.readinglist.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.readinglist.repositories.BookRepository;
import com.example.readinglist.jpa.Book;
import com.example.readinglist.exceptions.BookNotFoundException;

@RestController
public class BookController {
    
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String home() {
        return "Hello world";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/books")
    public List<Book> all() {
        return bookRepository.findAll();
    }
    
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/books")
    public Book newBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
    
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookRepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException(id));
    }
    
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/books/{id}")
    public Book replaceBook (@RequestBody Book newBook, @PathVariable Long id) {
        return bookRepository.findById(id)
        .map(book -> {
            book.setReader(newBook.getReader());
            book.setIsbn(newBook.getIsbn());
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setAuthor(newBook.getDescription());
            return bookRepository.save(book);
        })
        .orElseGet(() -> {
            newBook.setId(id);
            return bookRepository.save(newBook);
        });
    }
    
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
