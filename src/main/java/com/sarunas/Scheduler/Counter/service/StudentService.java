package com.sarunas.Scheduler.Counter.service;

import com.sarunas.Scheduler.Counter.model.Student;
import com.sarunas.Scheduler.Counter.model.StudentDto;
import com.sarunas.Scheduler.Counter.model.StudentResponse;

public interface StudentService {

    StudentResponse makeSchedule(StudentDto studentDto);
    Student getScheduleByStudentId(Long id);

}
