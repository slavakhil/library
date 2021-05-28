package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.User;
import com.example.MyBookShopApp.data.UserAuth;
import com.example.MyBookShopApp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller
@Scope("session")
public class LoginController {

    private UserRepo userRepo;

    private static User user;

    public static User getUser() {
        return user;
    }

    private static String userBeanName;

    public String getUserBeanName() {
        return userBeanName;
    }

    @Autowired
    public LoginController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/login")
    public String getPage(Model model){
        Logger.getLogger(LoginController.class.getName()).info("GET: return login"+this);
        userBeanName=this.toString();
        model.addAttribute("userAuth",new UserAuth());
        //model.addAttribute("userRole",user);
        return "log";
    }

    @PostMapping ("/auth")
    public String auth(UserAuth userAuth,Model model){
        user = userRepo.findByUsername(userAuth.getUsername());
        if(user!=null&&userAuth.getPassword().equals(user.getPassword())){
            model.addAttribute("userRole",user);
            return "redirect:/";
        }
        return "redirect:/login";
    }
}
