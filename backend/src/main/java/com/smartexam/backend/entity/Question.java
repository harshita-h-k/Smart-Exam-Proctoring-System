package com.smartexam.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {


    @Column(nullable = false)
    private Long examId;


    @Column(length = 1000)
    private String questionText;


    private String optionA;


    private String optionB;


    private String optionC;


    private String optionD;


    private String correctAnswer;


    private Integer marks;

}