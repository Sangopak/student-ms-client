package com.sango.microservices.client.service;

import com.sango.microservices.client.model.StudentDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class StudentDetailProxyServiceFallback implements StudentDetailProxyService {
    @Override
    public List<StudentDetailResponse> getAllStudents() {
        log.error("Fallback method triggered for getAllStudents");
        return new ArrayList<StudentDetailResponse>();
    }

    @Override
    public Optional<StudentDetailResponse> getStudentById(String id) {
        log.error("Fallback method triggered for getStudentById for if {}",id);
        return Optional.of(new StudentDetailResponse());
    }
}
