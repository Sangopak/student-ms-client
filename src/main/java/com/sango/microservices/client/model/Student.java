package com.sango.microservices.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_detail_response")
public class Student {
    @Id
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
