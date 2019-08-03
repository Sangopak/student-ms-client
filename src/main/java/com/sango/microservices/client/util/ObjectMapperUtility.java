package com.sango.microservices.client.util;

import com.sango.microservices.client.model.Student;
import com.sango.microservices.client.model.StudentDTO;

import java.util.Date;

public class ObjectMapperUtility {
    private ObjectMapperUtility() {
    }

    public static StudentDTO transformStudentToStudentResponse (Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setDob(student.getDob());
        studentDTO.setStreet(student.getStreet());
        studentDTO.setStreet2(student.getStreet2());
        studentDTO.setState(student.getState());
        studentDTO.setCity(student.getCity());
        studentDTO.setZip(student.getZip());
        studentDTO.setCourse(student.getCourse());
        studentDTO.setAsOfDate(new Date());
        return studentDTO;
    }

}
