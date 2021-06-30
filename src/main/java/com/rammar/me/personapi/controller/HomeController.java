package com.rammar.me.personapi.controller;

import com.rammar.me.personapi.utils.MyStrings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String bemVindo() {
        return MyStrings.BEM_VINDO;
    }

}
