package com.lihao.bookstore.repositories;

import com.lihao.bookstore.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAuthorByISBN(String bookISBN);
    List<Author> findAuthorByName(String name);
}
