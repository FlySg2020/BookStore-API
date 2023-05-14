package com.lihao.bookstore.entities;

import jakarta.persistence.Table;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Author")
public class Author implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID", updatable = false, nullable = false)
        private Long id;
        @NotEmpty(message = "Author name can not be empty!")
        @Column(name = "NAME")
        private String name;
        @Column(name = "BIRTHDAY")
        private Date birthday;
        @NotEmpty(message = "Book ISBN can not be empty!")
        @Column(name = "BOOK_ISBN")
        private String bookIsbn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }
        public Author(String name, Date birthday,String bookIsbn) {
            this.name = name;
            this.birthday = birthday;
            this.bookIsbn = bookIsbn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
}
