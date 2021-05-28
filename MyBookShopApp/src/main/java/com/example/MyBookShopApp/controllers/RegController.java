package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.User;
import com.example.MyBookShopApp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RegController {

    private UserRepo userRepo;

    @Autowired
    public RegController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String getReg(Model model){
        model.addAttribute("user",new User());
        return "signin";
    }

    @PostMapping("/registration")
    public String addUser(User user,Model model){
        for(User check: userRepo.findAll()){
            if(check.getUsername().equals(user.getUsername())){
                return "redirect:/registration";
            }
        }
        userRepo.save(user);
        return "redirect:/";
    }

}
