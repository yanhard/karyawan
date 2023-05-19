package com.hrd.karyawan.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.hrd.karyawan.dto.KaryawanTrainingDTO;
import com.hrd.karyawan.dto.KaryawanTrainingUpdateDTO;
import com.hrd.karyawan.services.KaryawanTrainingService;
import com.hrd.karyawan.entities.KaryawanTraining;
import com.hrd.karyawan.exception.CustomException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/idstar")
public class KaryawanTrainingController {

    @Autowired
    private KaryawanTrainingService karyawanTrainingService;

    @PostMapping("/karyawan-training/save")
    public ResponseEntity<?> saveKaryawanTraining(@Valid @RequestBody KaryawanTrainingDTO karyawanTrainingDTO) {
        try {
            KaryawanTraining karyawanTraining = karyawanTrainingService.saveKaryawanTraining(karyawanTrainingDTO);
            return ResponseEntity.ok(new ApiResponse<>(200, karyawanTraining, "sukses"));
        } catch (Exception e) {
            throw new CustomException("Data tidak ditemukan");
        }
    }

    @PutMapping("/karyawan-training/update")
    public ResponseEntity<?> updateKaryawanTraining(
            @Valid @RequestBody KaryawanTrainingUpdateDTO karyawanTrainingUpdateDTO) {
        try {

        } catch (Exception e) {
            throw new CustomException("Data tidak ditemukan");
        }
        KaryawanTraining karyawanTraining = karyawanTrainingService.updateKaryawanTraining(karyawanTrainingUpdateDTO);
        return ResponseEntity.ok(new ApiResponse<>(200, karyawanTraining, "sukses"));
    }

    @GetMapping("/karyawan-training/list")
    public ResponseEntity<?> getKaryawanTrainingList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<KaryawanTraining> karyawanTrainingPage = karyawanTrainingService.getKaryawanTrainingList(pageable);
            return ResponseEntity.ok(new ApiResponse<>(200, karyawanTrainingPage, "sukses"));
        } catch (Exception e) {
            throw new CustomException("Data tidak ditemukan");
        }
    }

    @GetMapping("/karyawan-training/{id}")
    public ResponseEntity<?> getKaryawanTrainingById(@PathVariable Long id) {
        try {
            Optional<KaryawanTraining> karyawanTrainingOptional = karyawanTrainingService.getKaryawanTrainingById(id);

            if (karyawanTrainingOptional.isPresent()) {
                return ResponseEntity.ok(new ApiResponse<>(200, karyawanTrainingOptional.get(), "sukses"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(404, "KaryawanTraining not found with id: " + id, "sukses"));
            }
        } catch (Exception e) {
            throw new CustomException("Data tidak ditemukan");
        }
    }

    @DeleteMapping("/karyawan-training/delete")
    public ResponseEntity<?> deleteKaryawanTraining(@RequestBody Map<String, Long> request) {
        try {
            Long id = request.get("id");
            karyawanTrainingService.deleteKaryawanTrainingById(id);
            return ResponseEntity.ok(new ApiResponse<>(200, "Sukses", "sukses"));
        } catch (Exception e) {
            throw new CustomException("Data tidak ditemukan");
        }
    }
}
