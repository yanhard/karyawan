package com.hrd.karyawan.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hrd.karyawan.dto.TrainingDTO;
import com.hrd.karyawan.entities.Training;

public interface TrainingService {
    Training saveTraining(TrainingDTO trainingDTO);
    Training updateTraining(Long id, TrainingDTO trainingDTO);
    Page<Training> getAllTrainings(Pageable pageable);
    Optional<Training> getTrainingById(Long id);
    void deleteTrainingById(Long id);
}
