package com.lihao.bookstore.services;

import com.lihao.bookstore.entities.Author;
import com.lihao.bookstore.exceptions.ConstraintException;
import com.lihao.bookstore.exceptions.NotFoundException;
import com.lihao.bookstore.repositories.AuthorRepository;
import com.lihao.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lihao.bookstore.entities.Book;

import java.util.List;
import java.util.ArrayList;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    private boolean exists(String isbn) {
        return bookRepository.findByISBN(isbn).isPresent();
    }
    public Book save(Book book){
        if(exists(book.getIsbn())){
            throw new ConstraintException(String.format("An existing book with the same ISBN [%s] was found in database.", book.getIsbn()));
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Book book){
        try{
            if(book != null && exists(book.getIsbn())){
                return  bookRepository.save(book);
            }
        }catch (Exception e){
            throw new NotFoundException(String.format("No such record with ISBN [%d] was found in database.", book.getIsbn()));
        }
        return null;
    }
    public void deleteBook(String isbn) {
        try {
            if (isbn != null && !"".equals(isbn) && exists(isbn)) {
                bookRepository.delete(bookRepository.findByISBN(isbn).get());
            }
        } catch (Exception e) {
            throw new NotFoundException(String.format("No such record with ISBN [%d] was found in database.", isbn));
        }
    }
//    public List<Book> findByTitleAndAuthor(String title, String author){
//        try{
//          return bookRepository.findByTitleAndAuthor(title,author);
//        }catch (Exception e){
//            throw new NotFoundException(String.format("No such record with author [%d] was found in database.", author));
//        }
//    }
    public List<Book> findByAuthor(String authorName){
        try{
            List<Author> authors = authorRepository.findAuthorByName(authorName);
            List<Book> bookList = new ArrayList<Book>();
            if(authors != null && authors.size() > 0){
                Book book = null;
                for (Author author: authors) {
                    book = bookRepository.findByISBN(author.getBookIsbn()).get();
                    book.setAuthors(authors);
                    bookList.add(book);
                }
            }
            return bookList;
        }catch (Exception e){
            throw new NotFoundException(String.format("No such record with author [%d] was found in database.", authorName));
        }
    }
    public Book findByTitle(String title) {

        try{
            Book book = bookRepository.findByTitle(title).get();
            if(book !=null && !"".equals(book.getIsbn())){
                List<Author> authors = authorRepository.findAuthorByISBN(book.getIsbn());
                if(authors != null && authors.size() > 0){
                    book.setAuthors(authors);
            }
        }
            return book;
        }catch(Exception e){
            throw new NotFoundException(String.format("No such record with title [%d] was found in database.", title));
        }
    }
}
