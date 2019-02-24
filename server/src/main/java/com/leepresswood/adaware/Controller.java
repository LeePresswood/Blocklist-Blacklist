package com.leepresswood.adaware;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private static final String template = "Hello, %s!";

    @GetMapping("/{name}")
    public String hello(@PathVariable String name) {
        return String.format(template, name);
    }
}