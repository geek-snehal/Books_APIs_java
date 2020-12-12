package com.example.BackendApp1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hi")
    public String sayHello() {
        return "Hello coders!! This is first API";

    }

    @GetMapping("/convert")
    public int convertToINR(@RequestParam int q){
        int usd = q;
        return 74 * q;
    }
}
