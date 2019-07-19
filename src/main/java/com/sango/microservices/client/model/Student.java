package com.sango.microservices.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private UUID id;
    private String name;
    private Date dob;
    private String street;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private String course;
}
