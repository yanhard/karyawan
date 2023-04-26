package com.hrd.karyawan.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TrainingDTO {
    private Long id;
    private String tema;
    private String pengajar;
    private LocalDateTime createdDate;
    private LocalDateTime deletedDate;
    private LocalDateTime updatedDate;

}

