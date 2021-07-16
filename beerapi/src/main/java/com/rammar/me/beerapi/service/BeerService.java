package com.rammar.me.beerapi.service;

import com.rammar.me.beerapi.dto.BeerDTO;
import com.rammar.me.beerapi.entity.Beer;
import com.rammar.me.beerapi.exceptions.BeerDuplicateException;
import com.rammar.me.beerapi.exceptions.BeerNotFoundException;
import com.rammar.me.beerapi.exceptions.BeerOverflowStockException;
import com.rammar.me.beerapi.mapper.BeerMapper;
import com.rammar.me.beerapi.repository.BeerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.beerMapper;

    public BeerDTO create(BeerDTO beerDTO) throws BeerDuplicateException {
        verifyExistsBeerName(beerDTO.getName());
        verifyExistsBeerBrand(beerDTO.getBrand());

        Beer beer = beerMapper.toModel(beerDTO);
        Beer savedBeer = beerRepository.save(beer);

        return beerMapper.toDTO(savedBeer);
    }

    public BeerDTO findByName(String name) throws BeerNotFoundException {
        Beer foundBeer = beerRepository.findByName(name).orElseThrow(() -> new BeerNotFoundException(name));
        return BeerMapper.beerMapper.toDTO(foundBeer);
    }

    public void delete(Long id) throws BeerNotFoundException {
        verifyExistsById(id);
        beerRepository.deleteById(id);
    }

    public List<BeerDTO> listAll() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Beer verifyExistsById(Long id) throws BeerNotFoundException {
        return beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException("There is no beer with id " + id));
    }

    private void verifyExistsBeerName(String name) throws BeerDuplicateException {
        Optional<Beer> optSavedBeer = beerRepository.findByName(name);
        if (optSavedBeer.isPresent()) {
            throw new BeerDuplicateException("There is already a beer with the same name " + name);
        }
    }

    private void verifyExistsBeerBrand(String brand) throws BeerDuplicateException {
        Optional<Beer> optSavedBeer = beerRepository.findByBrand(brand);
        if (optSavedBeer.isPresent()) {
            throw new BeerDuplicateException("There is already a beer with the same brand " + brand);
        }
    }

    public BeerDTO increment(Long id, Integer increment) throws BeerNotFoundException, BeerOverflowStockException {
        Beer foundedBeer = verifyExistsById(id);

        if (foundedBeer.getQuantity() + increment < foundedBeer.getMax()) {
            foundedBeer.setQuantity(foundedBeer.getQuantity() + increment);
            return beerMapper.toDTO(foundedBeer);
        } else {
            throw new BeerOverflowStockException(id, foundedBeer.getMax());
        }
    }
}
