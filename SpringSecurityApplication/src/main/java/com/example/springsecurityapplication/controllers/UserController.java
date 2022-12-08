package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/index")
    public String index(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        String role = personDetails.getPerson().getRole();

        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        return "user/index";
    }
}
