package com.rammar.me.personapi.service;

import com.rammar.me.personapi.dto.MessageDTO;
import com.rammar.me.personapi.dto.PersonDTO;
import com.rammar.me.personapi.dto.StudentDTO;
import com.rammar.me.personapi.entity.Person;
import com.rammar.me.personapi.entity.Student;
import com.rammar.me.personapi.exceptions.PersonNotFoundException;
import com.rammar.me.personapi.mapper.StudentMapper;
import com.rammar.me.personapi.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentService {

    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    public MessageDTO saveStudent(StudentDTO studentDTO) {
        Person person = studentMapper.toModel(studentDTO);
        return MessageDTO.builder().message("Created new student with number: " + person.getId()).build();
    }

    public List<PersonDTO> listAll() {
        return studentRepository.findAll().stream().map(studentMapper::toDTO).collect(Collectors.toList());
    }

    public StudentDTO findById(Long id) throws PersonNotFoundException {
        Student person = verifyById(id);
        return studentMapper.toDTO(person);
    }

    public MessageDTO delete(Long id) throws PersonNotFoundException {
        verifyById(id);
        studentRepository.deleteById(id);
        return MessageDTO.builder().message("Person " + id + " deleted.").build();
    }

    public MessageDTO update(Long id, PersonDTO studentDTO) throws PersonNotFoundException {
        verifyById(id);
        Student toUpdate = studentMapper.toModel((StudentDTO) studentDTO);
        studentRepository.save(toUpdate);
        return MessageDTO.builder().message("Saved person with ID: " + id).build();
    }

    private Student verifyById(Long id) throws PersonNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

}
