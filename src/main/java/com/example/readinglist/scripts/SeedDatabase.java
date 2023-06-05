package com.example.readinglist.scripts;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.readinglist.jpa.Book;
// import com.example.readinglist.jpa.User;
import com.example.readinglist.repositories.BookRepository;
import com.example.readinglist.repositories.UserRepository;

@Configuration
public class SeedDatabase {
    
    private static final Logger logger = LoggerFactory.getLogger(SeedDatabase.class);

    @Bean
    CommandLineRunner initialDatabase(BookRepository br, UserRepository ur, BCryptPasswordEncoder encoder) {
        return args -> {
            // logger.info("Preloading " + ur.save(new User("user", "user fullname", encoder.encode("password"), "ROLE_USER")));
            // logger.info("Preloading " + ur.save(new User("admin", "admin fullname", encoder.encode("password"), "ROLE_USER,ROLE_ADMIN")));
            logger.info("Preloading " + br.save(new Book("reader 1", "isbn 1", "title 1", "author 1", "description 1")));
            logger.info("Preloading " + br.save(new Book("reader 2", "isbn 2", "title 2", "author 2", "description 2")));
        };
    }
}
