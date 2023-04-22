package com.boilerplate.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/1")
    public String index() {
        return "index-1";
    }

    @GetMapping("person-form")
    public String personForm() {
        return "person_form";
    }
}
