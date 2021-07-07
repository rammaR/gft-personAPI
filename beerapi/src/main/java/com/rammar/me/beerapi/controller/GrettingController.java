package com.rammar.me.beerapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Greeting")
public class GrettingController {

    @ApiOperation(value = "Greets the World or Campinas")
    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello(@RequestParam(required = false) boolean campinas) {
        return "Hello Modular";
    }

}
