package com.sarunas.Scheduler.Counter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "work_scope")
    private Integer workScope;
    @Column(name = "completion_date")
    private LocalDate completionDate;
    @OneToMany(targetEntity = WorkingTimeEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable = false)
    private List<WorkingTimeEntity> workingTimes;

}



