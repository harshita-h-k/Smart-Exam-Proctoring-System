package com.smartexam.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="login_history")
public class LoginHistory extends BaseEntity {


    private Long userId;


    private String ipAddress;


    private String browser;


    private String operatingSystem;


    private boolean successful;

}