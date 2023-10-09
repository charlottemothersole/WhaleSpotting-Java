package com.whalespottingjava.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiController {
    @GetMapping("/api")
    public String getApiDocPage() {
        return "api";
    }
}
