package com.lihao.bookstore.entities;

import jakarta.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Book")
public class Book implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID", updatable = false, nullable = false)
        private Long id;
        @NotEmpty(message = "Book ISBN can not be empty!")
        @Column(name = "ISBN")
        private String isbn;
        @Column(name = "TITLE")
        private String title;
        private List<Author> authors;
        @Column(name = "YEAR")
        private int year;
        @Column(name = "PRICE")
        private double price;
        @Column(name = "GENRE")
        private String genre;

        public Book(String isbn, String title, int year, double price, String genre) {
            this.isbn = isbn;
            this.title = title;
            this.year = year;
            this.price = price;
            this.genre = genre;
        }

    public Book(Long id, String isbn, String title, List<Author> authors, int year, double price, String genre) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.price = price;
        this.genre = genre;
    }

    // Getters and setters
        public Long getId() {return id;}
        public void setId(Long id) {this.id = id;}
        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Author> getAuthors() {
            return authors;
        }

        public void setAuthors(List<Author> authors) {
            this.authors = authors;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }
}
