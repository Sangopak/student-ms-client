package com.sango.microservices.client.service;

import com.sango.microservices.client.model.Student;
import com.sango.microservices.client.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class StudentDetailProxyServiceFallback implements StudentDetailProxyService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        log.error("Fallback method triggered for getAllStudents, getting data from local data store");
        List<Student> studentListFromLocalDataStore = studentRepository.findAll();
        return studentListFromLocalDataStore;
    }

    @Override
    public Optional<Student> getStudentById(String id) {
        log.error("Fallback method triggered for getStudentById for if {}",id);
        Optional<Student> studentByIdFromLocalDataStore = studentRepository.findById(UUID.fromString(id));
        return studentByIdFromLocalDataStore;
    }
}
