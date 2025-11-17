package com.app.functinalInterfaces.controller;

import java.util.List;
import java.util.function.IntUnaryOperator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.functinalInterfaces.Entity.Student;
import com.app.functinalInterfaces.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PatchMapping("/{id}/increaseFee")
    public ResponseEntity<Student> increaseFee(@PathVariable Long id, @RequestParam int amount) {
        return studentService.increaseStudentFee(id, amount);
    }

    @PatchMapping("/{id}/decreaseFee")
    ResponseEntity<Student> decreaseFee(@PathVariable Long id, @RequestParam int amount) {
        return studentService.decreaseStudentFee(id, amount);

    }

    @PatchMapping("/{id}/increaseFeeWithPercent")
    public ResponseEntity<Student> increaseFeeByPercent(@PathVariable Long id, @RequestParam Double percent) {

        // percent = 10 means +10%
        IntUnaryOperator policy = currentFee -> {
            double multiplier = 1 + (percent / 100.0);
            long updated = Math.round(currentFee * multiplier);
            if (updated > Integer.MAX_VALUE) {
                throw new ArithmeticException("Fee Overflow");
            }
            return (int) updated;
        };
        return studentService.updateFee(id, policy);

    }

    @PatchMapping("/{id}/decreaseWithPercent")
    public ResponseEntity<Student> decreaseWithPercent(@PathVariable Long id, @RequestParam Double percent) {
        // percent 10 =means -10%
        IntUnaryOperator policy = currentFee -> {
            double multiplier = 1 - (percent / 100.0);
            long updated = Math.round(currentFee * multiplier);
            if (updated < Integer.MIN_VALUE) {
                throw new ArithmeticException("Fee amount getting collapse");
            }
            return (int) updated;
        };
        return studentService.updateFee(id, policy);
    }

    @PatchMapping("/{id}/address")
    public ResponseEntity<Student> updateAddress(@PathVariable Long id, @RequestParam("value") String newAddress) {
        return studentService.updateStudent(id, s -> {
            s.setAddress(newAddress);
            System.out.println("Added new method");
            return s;
        });

    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        return studentService.getAllStudent();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> getStudentById(@RequestParam int id) {
        return studentService.getStudentById(id);
    }
}
