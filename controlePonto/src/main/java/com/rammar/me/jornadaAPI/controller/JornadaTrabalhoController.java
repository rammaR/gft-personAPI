package com.rammar.me.jornadaAPI.controller;

import com.rammar.me.jornadaAPI.exceptions.JornadaNotFoundExcetion;
import com.rammar.me.jornadaAPI.entity.JornadaTrabalho;
import com.rammar.me.jornadaAPI.service.JornadaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jornada")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JornadaTrabalhoController {

    private final JornadaService jornadaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JornadaTrabalho createJornada(@RequestBody JornadaTrabalho jornadaTrabalho){
        return this.jornadaService.saveJornada(jornadaTrabalho);
    }

    @GetMapping
    public List<JornadaTrabalho> listAll(){
        return jornadaService.listAll();
    }

    @GetMapping(value = "/{id}")
    public JornadaTrabalho findById(@PathVariable Long id) throws JornadaNotFoundExcetion {
        return jornadaService.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long id) throws JornadaNotFoundExcetion {
        jornadaService.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public JornadaTrabalho update(@PathVariable Long id, @RequestBody JornadaTrabalho jornadaTrabalho) throws JornadaNotFoundExcetion {
        return jornadaService.update(id, jornadaTrabalho);
    }

}
