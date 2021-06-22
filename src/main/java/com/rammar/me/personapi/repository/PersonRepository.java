package com.rammar.me.personapi.repository;

import com.rammar.me.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {


}
