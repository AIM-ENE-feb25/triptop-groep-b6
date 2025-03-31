package com.prototype.triptop.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TransitController {
    @GetMapping("api/getAllData")
    public String getMethodName() {
        return new String();
    }

}
