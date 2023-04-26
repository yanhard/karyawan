package com.hrd.karyawan.services;

import com.hrd.karyawan.entities.KaryawanTraining;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.hrd.karyawan.dto.KaryawanTrainingDTO;
import com.hrd.karyawan.dto.KaryawanTrainingUpdateDTO;

public interface KaryawanTrainingService {
    KaryawanTraining saveKaryawanTraining(KaryawanTrainingDTO karyawanTrainingDTO);
    KaryawanTraining updateKaryawanTraining(KaryawanTrainingUpdateDTO karyawanTrainingUpdateDTO);
    Page<KaryawanTraining> getKaryawanTrainingList(Pageable pageable);
    Optional<KaryawanTraining> getKaryawanTrainingById(Long id);
    void deleteKaryawanTrainingById(Long id);
}
