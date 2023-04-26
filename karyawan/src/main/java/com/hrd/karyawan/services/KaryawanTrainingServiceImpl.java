package com.hrd.karyawan.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hrd.karyawan.dto.KaryawanTrainingDTO;
import com.hrd.karyawan.dto.KaryawanTrainingUpdateDTO;
import com.hrd.karyawan.entities.Karyawan;
import com.hrd.karyawan.entities.KaryawanTraining;
import com.hrd.karyawan.entities.Training;
import com.hrd.karyawan.repositories.KaryawanRepository;
import com.hrd.karyawan.repositories.KaryawanTrainingRepository;
import com.hrd.karyawan.repositories.TrainingRepository;

@Service
public class KaryawanTrainingServiceImpl implements KaryawanTrainingService {

    @Autowired
    private KaryawanRepository karyawanRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private KaryawanTrainingRepository karyawanTrainingRepository;

    @Override
    public KaryawanTraining saveKaryawanTraining(KaryawanTrainingDTO karyawanTrainingDTO) {
        Karyawan karyawan = karyawanRepository.findById(karyawanTrainingDTO.getKaryawan().getId())
                .orElseThrow(() -> new IllegalArgumentException("Karyawan not found with id: " + karyawanTrainingDTO.getKaryawan().getId()));
        
        Training training = trainingRepository.findById(karyawanTrainingDTO.getTraining().getId())
                .orElseThrow(() -> new IllegalArgumentException("Training not found with id: " + karyawanTrainingDTO.getTraining().getId()));
        
        KaryawanTraining karyawanTraining = new KaryawanTraining();
        karyawanTraining.setKaryawan(karyawan);
        karyawanTraining.setTraining(training);
        karyawanTraining.setTanggal(karyawanTrainingDTO.getTanggal());
        
        return karyawanTrainingRepository.save(karyawanTraining);
    }
    
    @Override
    public KaryawanTraining updateKaryawanTraining(KaryawanTrainingUpdateDTO karyawanTrainingUpdateDTO) {
        KaryawanTraining karyawanTraining = karyawanTrainingRepository.findById(karyawanTrainingUpdateDTO.getId())
            .orElseThrow(() -> new IllegalArgumentException("KaryawanTraining not found with id: " + karyawanTrainingUpdateDTO.getId()));
    
        Karyawan karyawan = karyawanRepository.findById(karyawanTrainingUpdateDTO.getKaryawan().getId())
            .orElseThrow(() -> new IllegalArgumentException("Karyawan not found with id: " + karyawanTrainingUpdateDTO.getKaryawan().getId()));
    
        Training training = trainingRepository.findById(karyawanTrainingUpdateDTO.getTraining().getId())
            .orElseThrow(() -> new IllegalArgumentException("Training not found with id: " + karyawanTrainingUpdateDTO.getTraining().getId()));
    
        karyawanTraining.setKaryawan(karyawan);
        karyawanTraining.setTraining(training);
        karyawanTraining.setTanggal(karyawanTrainingUpdateDTO.getTanggal());
    
        return karyawanTrainingRepository.save(karyawanTraining);
    }
    
    @Override
    public Page<KaryawanTraining> getKaryawanTrainingList(Pageable pageable) {
        return karyawanTrainingRepository.findAll(pageable);
    }

    @Override
    public Optional<KaryawanTraining> getKaryawanTrainingById(Long id) {
        return karyawanTrainingRepository.findById(id);
    }

    @Override
    public void deleteKaryawanTrainingById(Long id) {
        karyawanTrainingRepository.deleteById(id);
    }
}



