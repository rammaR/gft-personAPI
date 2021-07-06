package com.rammar.me.personapi.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("STUDENT")
public class Student extends Person {

    @Column(nullable = false, unique = true)
    private Long registration;

}
