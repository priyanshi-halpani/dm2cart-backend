package com.appDost.DM2Cart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AdminController {
    @GetMapping("/home")
    public String home() {
        return "Welcome Admin!";
    }
}