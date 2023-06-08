package com.sarunas.Scheduler.Counter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentResponse {

    private Long id;
    private String name;
    private List<WorkingTime> workingTimes;
}
