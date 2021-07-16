package com.rammar.me.beerapi.controller;

import com.rammar.me.beerapi.dto.BeerDTO;
import com.rammar.me.beerapi.entity.Beer;
import com.rammar.me.beerapi.exceptions.BeerDuplicateException;
import com.rammar.me.beerapi.exceptions.BeerNotFoundException;
import com.rammar.me.beerapi.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerController {

    private final BeerService beerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDTO createBeer(@RequestBody @Valid BeerDTO beerDTO) throws BeerDuplicateException {
        return beerService.create(beerDTO);
    }

    @GetMapping(value = "/{name}")
    public BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException {
        return beerService.findByName(name);
    }

    @GetMapping
    public List<BeerDTO> litBeers(){
        return beerService.listAll();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws BeerNotFoundException {
        beerService.delete(id);
    }

}
