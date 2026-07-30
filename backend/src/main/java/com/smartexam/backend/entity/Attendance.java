package com.smartexam.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "attendance")
public class Attendance extends BaseEntity {

    private Long studentId;

    private Long examId;

    private boolean present;

    private LocalDateTime loginTime;

    private LocalDateTime logoutTime;

    private Long duration;

}