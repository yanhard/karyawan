package com.hrd.karyawan.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class KaryawanTrainingUpdateDTO {
    private Long id;
    private KaryawanIdDTO karyawan;
    private TrainingIdDTO training;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tanggal;

}

