package com.hrd.karyawan.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Karyawan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime deletedDate;
    private LocalDateTime updatedDate;
    private String alamat;
    private LocalDate dob;
    private String nama;
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_karyawan_id", referencedColumnName = "id")
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
    //@JsonManagedReference
    @JsonIgnore
    private List<Rekening> rekeningList;    

}


