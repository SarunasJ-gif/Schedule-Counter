package com.sarunas.Scheduler.Counter;

import com.sarunas.Scheduler.Counter.model.Student;
import com.sarunas.Scheduler.Counter.repository.StudentRepository;
import com.sarunas.Scheduler.Counter.service.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
class SchedulerCounterApplicationTests {


	@MockBean
	private StudentServiceImpl studentServiceImpl;

	@MockBean
	private StudentRepository studentRepository;

	@Test
	public void saveStudent() {
		Student student = new Student(1L, "Rob Stark", 8, LocalDate.of(2023, Month.JUNE, 15),
				List.of());
		when(studentRepository.save(student)).thenReturn(student);
		assertEquals(student, studentRepository.save(student));
	}
}
