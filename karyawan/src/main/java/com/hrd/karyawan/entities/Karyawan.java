package com.hrd.karyawan.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Karyawan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    private LocalDateTime createdDate;
    @JsonIgnore
    private LocalDateTime deletedDate;
    @JsonIgnore
    private LocalDateTime updatedDate;
    @JsonIgnore
    private String alamat;
    @JsonIgnore
    private LocalDate dob;
    private String nama;
    @JsonIgnore
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_karyawan_id", referencedColumnName = "id")
    @JsonIgnore
    private DetailKaryawan detailKaryawan;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();

    }

    @OneToMany(mappedBy = "karyawan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Rekening> rekeningList; 

    //@OneToMany(mappedBy = "karyawan", fetch = FetchType.LAZY)
    //private List<Training> trainingList;
}


