package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping
    public String getProfile() {
        try {
            LoginController.getUser().getADMIN();
            return "profile";
        } catch (Exception e) {
            Logger.getLogger(ProfileController.class.getName()).info("GET: user is invalid");
        }
        return "redirect:/";
    }
}
