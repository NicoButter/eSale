package com.esale.esale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FallbackController {

    @GetMapping("/{path:^(?!api|static|webjars|favicon\\.ico).*$}")
    public String redirect() {
        return "forward:/index.html";
    }

    @GetMapping("/**/{path:[^\\.]*}")
    public String forward() {
        return "forward:/index.html";
    }
}
