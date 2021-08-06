package com.rammar.me.jornadaAPI.service;

import com.rammar.me.jornadaAPI.exceptions.JornadaNotFoundExcetion;
import com.rammar.me.jornadaAPI.entity.JornadaTrabalho;
import com.rammar.me.jornadaAPI.repository.JornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JornadaService {

    @Autowired
    JornadaRepository jornadaRepository;

    public JornadaTrabalho saveJornada(JornadaTrabalho jornadaTrabalho) {
        return jornadaRepository.save(jornadaTrabalho);
    }

    public List<JornadaTrabalho> listAll() {
        return jornadaRepository.findAll();
    }

    public JornadaTrabalho findById(Long id) throws JornadaNotFoundExcetion {
        return returnIFExists(id);
    }

    public void deleteById(Long id) throws JornadaNotFoundExcetion {
        returnIFExists(id);
        jornadaRepository.deleteById(id);
    }

    public JornadaTrabalho update(Long id, JornadaTrabalho jornadaTrabalho) throws JornadaNotFoundExcetion {
        returnIFExists(id);
        return jornadaRepository.save(jornadaTrabalho);
    }

    private JornadaTrabalho returnIFExists(Long id) throws JornadaNotFoundExcetion {
        Optional<JornadaTrabalho> optional = jornadaRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        throw new JornadaNotFoundExcetion(id);
    }
}
