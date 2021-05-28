package com.example.MyBookShopApp.repos;

import com.example.MyBookShopApp.data.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
