package com.rammar.me.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    private Long id;
    private String address;
    private String block;
    private String city;
    private String state;
    private String phone;
}
