package com.ibm.hack.rabbot.rabbottest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitialController {

    @GetMapping(path = "/ping")
    public String pingTest(){
        return "OK";
    }

}
