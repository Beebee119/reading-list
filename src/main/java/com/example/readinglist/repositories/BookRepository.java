package com.example.readinglist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.readinglist.jpa.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    
}
