package com.app.functinalInterfaces.services;

import com.app.functinalInterfaces.Entity.Student;
import com.app.functinalInterfaces.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<Student> saveStudent(Student student) {
        Student saved = studentRepository.save(student);
        return ResponseEntity.ok(saved);
    }
    /*
    increase fee using fixed amount using functional interface
     */

    public ResponseEntity<Student> increaseStudentFee(Long studentId, int amount) {

        if (amount <= 0) {
            return ResponseEntity.badRequest().build();
        }
        IntUnaryOperator adjuster = fee -> Math.addExact(fee, amount);
        return updateFee(studentId, adjuster);
    }

    public ResponseEntity<Student> decreaseStudentFee(Long studentId, int amount) {
        if (amount <= 0) {
            return ResponseEntity.badRequest().build();

        }
        IntUnaryOperator adjuster = fee -> Math.subtractExact(fee, amount);
        return updateFee(studentId, adjuster);
    }


    public ResponseEntity<Student> updateFee(Long studentId, IntUnaryOperator feePolicy) {

        return studentRepository.findById(studentId)
                .map(student -> {
                    int newFee = feePolicy.applyAsInt(student.getFee());
                    student.setFee(newFee);
                    Student updated = studentRepository.save(student);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    public ResponseEntity<Student> updateStudent(Long student, UnaryOperator<Student> updater) {
        return studentRepository.findById(student)
                .map(student1 -> {
                    Student toSave = updater.apply(student1);
                    Student updated = studentRepository.save(toSave);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Student>> getAllStudent(){
        return ResponseEntity.ok(studentRepository.findAll());

    }


}
