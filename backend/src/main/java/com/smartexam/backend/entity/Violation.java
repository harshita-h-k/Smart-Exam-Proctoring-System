package com.smartexam.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="violations")
public class Violation extends BaseEntity {


    private Long studentId;


    private Long examId;


    private String violationType;


    @Column(length = 1000)
    private String description;


    private Double confidenceScore;


    private String imagePath;

}