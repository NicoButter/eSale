package com.esale.esale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FallbackController {

    @RequestMapping(value = { "/{path:[^\\.]*}" })
    public String forward() {
        return "forward:/index.html";
    }
}