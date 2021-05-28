package com.example.MyBookShopApp.repos;

import com.example.MyBookShopApp.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book,Long> {
    List<Book> findBooksByName(String name);

    Book deleteById(int id);

    Book findBookById(int id);
}
