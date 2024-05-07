package com.ldf.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexShowController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

}
