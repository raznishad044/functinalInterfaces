package com.app.functinalInterfaces.repository;

import java.util.List;

import com.app.functinalInterfaces.Entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findById(int id);

}
