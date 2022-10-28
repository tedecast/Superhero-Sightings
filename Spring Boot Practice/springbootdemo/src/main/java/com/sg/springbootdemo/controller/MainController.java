/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Teresa
 */
@Controller
public class MainController {

    String name = "John";
    int number = 42;

    @GetMapping("test")
    public String testPage(Model model) {

        model.addAttribute("number", number);
        model.addAttribute("firstName", name);

        return "test";
    }
}
