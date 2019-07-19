package com.sango.microservices.client.util;

import com.sango.microservices.client.model.Student;
import com.sango.microservices.client.model.StudentResponse;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ObjectMapperUtility {
    private ObjectMapperUtility() {
    }

    public static StudentResponse transformStudentToStudentResponse (Student student){
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setDob(student.getDob());
        studentResponse.setStreet(student.getStreet());
        studentResponse.setStreet2(student.getStreet2());
        studentResponse.setState(student.getState());
        studentResponse.setCity(student.getCity());
        studentResponse.setZip(student.getZip());
        studentResponse.setCourse(student.getCourse());
        studentResponse.setAsOfDate(new Date());
        return studentResponse;
    }

}
