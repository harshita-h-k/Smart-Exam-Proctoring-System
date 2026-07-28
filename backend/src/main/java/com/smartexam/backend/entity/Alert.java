package com.smartexam.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="alerts")
public class Alert extends BaseEntity {


    private Long studentId;


    private Long examId;


    private String alertType;


    private String severity;


    private String message;


    private boolean resolved;

}