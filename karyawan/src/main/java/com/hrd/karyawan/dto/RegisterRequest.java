package com.hrd.karyawan.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String name;
    private String phone_number;
    private String domicile;
    private String gender;
    private String password;
    private String fullname;
}
