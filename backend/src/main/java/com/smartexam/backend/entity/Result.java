package com.smartexam.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="results")
public class Result extends BaseEntity {


    private Long studentId;


    private Long examId;


    private Double score;


    private String grade;


    private String status;


    private boolean malpracticeDetected;

}