package com.hrd.karyawan.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hrd.karyawan.dto.TrainingDTO;
import com.hrd.karyawan.entities.Training;
import com.hrd.karyawan.repositories.TrainingRepository;

@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public Training saveTraining(TrainingDTO trainingDTO) {
        Training training = new Training();
        training.setTema(trainingDTO.getTema());
        training.setPengajar(trainingDTO.getPengajar());
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Long id, TrainingDTO trainingDTO) {
        Training training = trainingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Training not found with id: " + id));
    
        training.setTema(trainingDTO.getTema());
        training.setPengajar(trainingDTO.getPengajar());
        return trainingRepository.save(training);        
    }
    
    @Override
    public Page<Training> getAllTrainings(Pageable pageable) {
        return trainingRepository.findAll(pageable);
    }

    @Override
    public Optional<Training> getTrainingById(Long id) {
        return trainingRepository.findById(id);
    }
    
    @Override
    public void deleteTrainingById(Long id) {
        trainingRepository.deleteById(id);
    }
    
}

