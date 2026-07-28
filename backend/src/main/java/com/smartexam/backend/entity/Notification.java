package com.smartexam.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="notifications")
public class Notification extends BaseEntity {


    private Long userId;


    private String title;


    @Column(length = 1000)
    private String message;


    private boolean readStatus;

}