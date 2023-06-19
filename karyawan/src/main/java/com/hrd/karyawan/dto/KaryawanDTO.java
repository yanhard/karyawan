package com.hrd.karyawan.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class KaryawanDTO {
    private Long id;
    private String nama;
    
    private LocalDate dob;
    private String status;
    private String alamat;
    private DetailKaryawanDTO detailKaryawan;
     
}



