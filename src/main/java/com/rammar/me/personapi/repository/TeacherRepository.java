package com.rammar.me.personapi.repository;

import com.rammar.me.personapi.entity.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}
