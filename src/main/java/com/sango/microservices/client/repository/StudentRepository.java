package com.sango.microservices.client.repository;

import com.sango.microservices.client.model.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentDTO, UUID> {
}
