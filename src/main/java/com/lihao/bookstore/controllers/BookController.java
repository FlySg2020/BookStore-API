package com.lihao.bookstore.controllers;

import com.lihao.bookstore.services.BookService;
import com.lihao.bookstore.entities.Book;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Add a new book to the database")
    @PreAuthorize("hasAnyRole('CHECKER', 'ADMIN')")
    @PostMapping(value = {"", "/"})
    public ResponseEntity<Book> addBook(@Validated @RequestBody Book book){
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @ApiOperation(value = "return all books from database")
    @PreAuthorize("hasAnyRole('CHECKER', 'ADMIN')")
    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Book>> getBooks(){
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }
    @ApiOperation(value = "return the book from database by its title ")
    @PreAuthorize("hasAnyRole('CHECKER', 'ADMIN')")
    @GetMapping("/{title}")
    public ResponseEntity<Book> getBooksByTitle(@PathVariable String title){
        return new ResponseEntity<>(bookService.findByTitle(title), HttpStatus.OK);
    }

    @ApiOperation(value = "return all books from database by its author")
    @PreAuthorize("hasAnyRole('CHECKER', 'ADMIN')")
    @GetMapping("/{authorName}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String authorName){
        return new ResponseEntity<>(bookService.findByAuthor(authorName), HttpStatus.OK);
    }


    @ApiOperation(value = "update an existing book by its ISBN")
    @PreAuthorize("hasAnyRole('CHECKER', 'ADMIN')")
    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @Validated @RequestBody Book book){
        book.setIsbn(isbn);
        return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an existing book from database by its ISBN")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn){
        bookService.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
