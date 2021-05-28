package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.SearchObject;
import com.example.MyBookShopApp.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.logging.Logger;

@Controller
@RequestMapping("/search")
public class Search {

    private BookRepo bookRepo;

    @Autowired
    public Search(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping
    public String getSearch(Model model) {
        model.addAttribute("booksFind",bookRepo.findAll());
        model.addAttribute("nameSearch",new SearchObject());
        return "search";
    }

    @PostMapping("/books")
    public String getBookName(SearchObject searchObject, Model model) {
        Logger.getLogger(Search.class.getName()).info("POST: books is find now");
        Logger.getLogger(Search.class.getName()).info(bookRepo.findBooksByName(searchObject.getName())+"");
        model.addAttribute("booksFind",bookRepo.findBooksByName(searchObject.getName()));
        model.addAttribute("nameSearch",new SearchObject());
        return "search";
    }
}

