package com.hrd.karyawan.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "karyawan_training")
public class KaryawanTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "karyawan_id", referencedColumnName = "id")
    private Karyawan karyawan;

    @ManyToOne
    @JoinColumn(name = "training_id", referencedColumnName = "id")
    private Training training;

    private LocalDateTime tanggal;

}

