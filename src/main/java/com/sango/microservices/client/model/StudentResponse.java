package com.sango.microservices.client.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponse {
    private List<Student> students;
}
