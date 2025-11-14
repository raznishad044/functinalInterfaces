package com.app.functinalInterfaces.controller;

import com.app.functinalInterfaces.Entity.Student;
import com.app.functinalInterfaces.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.IntUnaryOperator;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.saveStudent(student))
                .getBody();
    }

    @PatchMapping("/{id}/increaseFee")
    public ResponseEntity<Student> increaseFee(@PathVariable Long id, @RequestParam int amount) {
        return ResponseEntity.ok(studentService.increaseStudentFee(id, amount))
                .getBody();
    }


    @PatchMapping("/{id}/decreaseFee")
    ResponseEntity<Student> decreaseFee(@PathVariable Long id, @RequestParam int amount) {
        return ResponseEntity.ok(studentService.decreaseStudentFee(id, amount)).getBody();
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
        return ResponseEntity.ok(studentService.updateFee(id, policy)).getBody();
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
        return ResponseEntity.ok(studentService.updateFee(id, policy)).getBody();
    }


    @PatchMapping("/{id}/address")
    public ResponseEntity<Student> updateAddress(@PathVariable Long id, @RequestParam("value") String newAddress) {
        return ResponseEntity.ok(studentService.updateStudent(id, s -> {
                    s.setAddress(newAddress);
                    return s;
                }

        )).getBody();
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudent()).getBody();
    }
}
