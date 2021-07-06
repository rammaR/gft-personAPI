package com.rammar.me.personapi.repository;

import com.rammar.me.personapi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
