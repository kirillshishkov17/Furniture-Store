package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.services.ProductService;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    private final ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }
}
