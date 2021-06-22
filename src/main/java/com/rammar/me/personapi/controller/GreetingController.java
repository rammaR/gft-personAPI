package com.rammar.me.personapi.controller;

import com.rammar.me.personapi.dto.GreetingDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Greeting")
public class GreetingController {

    @ApiOperation(value = "Greets the World or Campinas")
    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public GreetingDTO hello(@RequestParam(required = false) boolean campinas) {
        GreetingDTO greetingDTO = new GreetingDTO("Hello World");
        if (campinas) {
            greetingDTO.setMessage(greetingDTO.getMessage().replace("World", "Campinas"));
        }
        return greetingDTO;
    }

    @ApiOperation(value = "Greets a person given her name")
    @GetMapping(value = "/hello/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GreetingDTO helloPerson(@PathVariable String name) {
        return new GreetingDTO("Hello " + name);
    }
}
