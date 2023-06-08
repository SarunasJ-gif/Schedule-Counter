package com.sarunas.Scheduler.Counter.repository;

import com.sarunas.Scheduler.Counter.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
