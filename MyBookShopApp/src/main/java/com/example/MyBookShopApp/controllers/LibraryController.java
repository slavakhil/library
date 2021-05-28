package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.User;
import com.example.MyBookShopApp.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
public class LibraryController {

    private BookRepo bookRepo;

    @Autowired
    public LibraryController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping(value = "/books")
    public String getLibrary(Model model) {
        Logger.getLogger(LibraryController.class.getName()).info("GET: return library/books" + "session");
        Logger.getLogger(LibraryController.class.getName()).info("GET: user is " + LoginController.class.getName()
                + " " + LoginController.getUser());
        Logger.getLogger(LibraryController.class.getName()).info("GET: session " + this);
        model.addAttribute("books", bookRepo.findAll());
        if (LoginController.getUser() != null) {
            Logger.getLogger(LibraryController.class.getName()).info("GET: session admin");
            model.addAttribute("userState", LoginController.getUser().getADMIN());
        } else {
            Logger.getLogger(LibraryController.class.getName()).info("GET: session user");
            model.addAttribute("userState", "user");
        }
        model.addAttribute("book", new Book());
        return "library";
    }

    @PostMapping(value = "/add")
    public String add(Book book, Model model) {
        try{
            if (LoginController.getUser().getADMIN().equals("admin")) {
                bookRepo.save(book);
                model.addAttribute("books", bookRepo.findAll());
                return "redirect:/books";
            }
            return "redirect:/books";
        }catch (Exception e){
            return "404";
        }
    }

    @PostMapping(value = "/remove/{id}")
    public String remove(@PathVariable (value="id")int id) {
        Logger.getLogger(LibraryController.class.getName()).info("POST: delete id is " + id);
        if (bookRepo.findBookById(id) == null) {
            return "redirect:/books";
        }
        bookRepo.delete(bookRepo.findBookById(id));
        return "redirect:/books";
    }
}
