package com.lihao.bookstore.services;

import com.lihao.bookstore.entities.Author;
import com.lihao.bookstore.exceptions.NotFoundException;
import com.lihao.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public List<Author> findAuthorByBookISBN(String bookISBN){
        try{
            return authorRepository.findAuthorByISBN(bookISBN);
        }catch(Exception e){
            throw new NotFoundException(String.format("No such record with ISBN [%d] was found in database.", bookISBN));
        }
    }
}
