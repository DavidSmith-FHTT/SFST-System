package com.ldf.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatasetPageController {

    @GetMapping("/dataset")
    public String dataset(Model model){
        return "datapage";
    }

}
