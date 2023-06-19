package com.hrd.karyawan.entities;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.standard.DateTimeContext;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "oauthusers")
@Data
public class OauthUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String fullname;
    private String phone_number;
    private String domicile;
    private String gender;
    private String password;
    private boolean enabled;
    private String otp;
    private LocalDateTime otpExpiredDate;
    private String status;
}
