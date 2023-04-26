package com.hrd.karyawan.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrd.karyawan.dto.TrainingDTO;
import com.hrd.karyawan.entities.Training;
import com.hrd.karyawan.services.TrainingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/idstar")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @PostMapping("/training/save")
    public ResponseEntity<?> saveTraining(@Valid @RequestBody TrainingDTO trainingDTO) {
        Training training = trainingService.saveTraining(trainingDTO);
        return ResponseEntity.ok(new ApiResponse<>(200, training));
    }
    
    @PutMapping("/training/update")
    public ResponseEntity<?> updateTraining(@Valid @RequestBody TrainingDTO trainingDTO) {
        Long id = trainingDTO.getId();
        Training training = trainingService.updateTraining(id, trainingDTO);
        return ResponseEntity.ok(new ApiResponse<>(200, training));
    }

    @GetMapping("/training/list")
    public ResponseEntity<?> getAllTrainings(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Training> trainingPage = trainingService.getAllTrainings(pageable);
        return ResponseEntity.ok(new ApiResponse<>(200, trainingPage));
    }

    @GetMapping("/training/{id}")
    public ResponseEntity<?> getTrainingById(@PathVariable Long id) {
        Training training = trainingService.getTrainingById(id)
                .orElseThrow(() -> new IllegalArgumentException("Training not found with id: " + id));
        return ResponseEntity.ok(new ApiResponse<>(200, training));
    }

    @DeleteMapping("/training/delete")
    public ResponseEntity<?> deleteTrainingById(@RequestBody Map<String, Long> requestBody) {
        Long id = requestBody.get("id");
        trainingService.deleteTrainingById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Sukses"));
    }

}

