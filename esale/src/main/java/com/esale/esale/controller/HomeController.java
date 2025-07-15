package com.esale.esale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        // Redirige a index.html (generado por Angular) en la carpeta static
        return "forward:/index.html";
    }
}