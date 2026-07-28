package com.smartexam.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "exams")
public class Exam extends BaseEntity {


    @Column(nullable = false)
    private String examName;


    private String subject;


    private Integer duration;


    private LocalDateTime startTime;


    private LocalDateTime endTime;


    private boolean active;

}