package com.sango.microservices.client.service;

import com.sango.microservices.client.model.Student;
import com.sango.microservices.client.model.StudentResponse;
import com.sango.microservices.client.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class StudentProxyServiceFallback implements StudentProxyService {

    @Override
    public StudentResponse getAllStudents() {
        log.error("Fallback method triggered for getAllStudents, getting data from local data store");
        return new StudentResponse();
    }

    @Override
    public Optional<Student> getStudentById(String id) {
        log.error("Fallback method triggered for getStudentById for if {}",id);
        return Optional.empty();
    }
}
