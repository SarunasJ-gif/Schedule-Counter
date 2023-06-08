package com.sarunas.Scheduler.Counter.controller;

import com.sarunas.Scheduler.Counter.model.Student;
import com.sarunas.Scheduler.Counter.model.StudentDto;
import com.sarunas.Scheduler.Counter.model.StudentResponse;
import com.sarunas.Scheduler.Counter.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> makeSchedule(@RequestBody StudentDto student) {
        StudentResponse studentResponse = studentService.makeSchedule(student);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        Student student = studentService.getScheduleByStudentId(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
