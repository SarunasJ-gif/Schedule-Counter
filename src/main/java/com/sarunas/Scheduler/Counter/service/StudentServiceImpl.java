package com.sarunas.Scheduler.Counter.service;

import com.sarunas.Scheduler.Counter.exceptions.NoDaysLeftException;
import com.sarunas.Scheduler.Counter.exceptions.NotEnoughTimeForTaskException;
import com.sarunas.Scheduler.Counter.exceptions.StudentNotFoundException;
import com.sarunas.Scheduler.Counter.model.*;
import com.sarunas.Scheduler.Counter.repository.StudentRepository;
import com.sarunas.Scheduler.Counter.repository.WorkingTimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final WorkingTimeRepository workingTimeRepository;

    @Override
    public StudentResponse makeSchedule(StudentDto studentDto) {
        StudentResponse studentResponse = new StudentResponse();
        List<WorkingTime> workingTimes = getWorkingTime(studentDto.getBusyDays(), studentDto.getCompletionDate());
        if (workingTimes.isEmpty()) {
            throw new NoDaysLeftException("You have no days to complete your task...");
        }
        int totalHours = studentDto.getWorkScope();
        int day = 24;
        int leisureHours = 17;
        while (totalHours >= 0) {
            if (leisureHours <= 0) {
                throw new NotEnoughTimeForTaskException("You have no enough time to finish project...");
            }
            for (WorkingTime workingTime : workingTimes) {
                int hourWorkPerDay = day - leisureHours - workingTime.getBusyHours();
                if ((totalHours - hourWorkPerDay) <= 0) {
                    workingTime.setHours(workingTime.getHours() + totalHours);
                    totalHours -= hourWorkPerDay;
                    break;
                } else {
                    workingTime.setBusyHours(workingTime.getBusyHours() + hourWorkPerDay);
                    workingTime.setHours(workingTime.getHours() + hourWorkPerDay);
                    totalHours -= hourWorkPerDay;
                }
            }
            leisureHours -= 1;
        }
        Student studentToSave = convertStudentToEntity(studentDto, workingTimes);
        Student student = studentRepository.save(studentToSave);
        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setWorkingTimes(workingTimes);
        return studentResponse;
    }

    @Override
    public Student getScheduleByStudentId(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new StudentNotFoundException("Such student doesn't exist!");
        }
        return optionalStudent.get();
    }


    private List<WorkingTime> getWorkingTime(List<BusyDay> busyDays, LocalDate endDate) {
        List<WorkingTime> workingTimes = new ArrayList<>();
        for (LocalDate start = LocalDate.now(); start.isBefore(endDate); start = start.plusDays(1)) {
            if (isHoliday(start)) {
                continue;
            }
            boolean isBusyDay = false;
            for (BusyDay busyDay : busyDays) {
                if (start.isEqual(busyDay.getDate())) {
                    workingTimes.add(new WorkingTime(busyDay.getDate(), 0, busyDay.getBusyHours()));
                    isBusyDay = true;
                    break;
                }
            }
            if (!isBusyDay) {
                workingTimes.add(new WorkingTime(start, 0, 0));
            }
        }
        return workingTimes;
    }


    private boolean isHoliday(LocalDate date) {
        LocalDate easternSunday = getEasterSunday(date.getYear());
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY) ||
                (date.getMonthValue() == 12 && date.getDayOfMonth() == 25) ||
                (date.getMonthValue() == 12 && date.getDayOfMonth() == 26) ||
                (date.getMonthValue() == 1 && date.getDayOfMonth() == 1) ||
                (date.getMonthValue() == 1 && date.getDayOfMonth() == 2) || date.equals(easternSunday);
    }


    private static LocalDate getEasterSunday(int year) {
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int L = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * L) / 451;
        int month = (h + L - 7 * m + 114) / 31;
        int day = ((h + L - 7 * m + 114) % 31) + 1;
        return LocalDate.of(year, month, day);
    }


    private Student convertStudentToEntity(StudentDto studentDto, List<WorkingTime> workingTimes) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setWorkScope(studentDto.getWorkScope());
        student.setCompletionDate(studentDto.getCompletionDate());
        List<WorkingTimeEntity> workingTimeEntities = workingTimes.stream()
                .map(this::convertWorkingTimeToEntity)
                .collect(Collectors.toList());
        student.setWorkingTimes(workingTimeEntities);
        return student;
    }

    private WorkingTimeEntity convertWorkingTimeToEntity(WorkingTime workingTime) {
        WorkingTimeEntity workingTimeEntity = new WorkingTimeEntity();
        workingTimeEntity.setDate(workingTime.getDate());
        workingTimeEntity.setHours(workingTime.getHours());
        return workingTimeEntity;
    }
}
