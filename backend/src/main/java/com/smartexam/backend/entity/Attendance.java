package com.smartexam.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="attendance")
public class Attendance extends BaseEntity {


    private Long studentId;


    private Long examId;


    private boolean present;


    private String loginTime;


    private String logoutTime;


    private Double duration;

}