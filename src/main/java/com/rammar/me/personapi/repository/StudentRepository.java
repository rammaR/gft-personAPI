package com.rammar.me.personapi.repository;

import com.rammar.me.personapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
