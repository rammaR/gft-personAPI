package com.rammar.me.personapi.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Student> students;

}
