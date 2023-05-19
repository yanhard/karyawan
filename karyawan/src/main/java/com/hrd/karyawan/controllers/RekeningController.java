package com.hrd.karyawan.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrd.karyawan.dto.RekeningDTO;
import com.hrd.karyawan.dto.RekeningDeleteDTO;
import com.hrd.karyawan.entities.Rekening;
import com.hrd.karyawan.exception.CustomException;
import com.hrd.karyawan.services.RekeningService;

@RestController
@RequestMapping("/v1/idstar")
public class RekeningController {

    @Autowired
    private RekeningService rekeningService;

    @PostMapping("/rekening/save")
    public ResponseEntity<?> saveRekening(@Valid @RequestBody RekeningDTO rekeningDTO) {
        try {
            Rekening rekening = rekeningService.saveRekening(rekeningDTO);
            return ResponseEntity.ok(new ApiResponse<>(200, rekening, "sukses"));
        } catch (Exception e) {
            throw new CustomException("Data tidak ditemukan");
        }
    }

    @PutMapping("/rekening/update")
    public ResponseEntity<?> updateRekening(@Valid @RequestBody RekeningDTO rekeningDTO) {
        try {
            Assert.notNull(rekeningDTO.getId(), "Rekening ID must not be null");
            Rekening updatedRekening = rekeningService.updateRekening(rekeningDTO);
            return ResponseEntity.ok(new ApiResponse<>(200, updatedRekening, "sukses"));
        } catch (Exception e) {
            throw new CustomException("Data tidak ditemukan");
        }
    }

    @GetMapping("/rekening/list")
    public ResponseEntity<?> listRekening(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
            Page<Rekening> rekeningPage = rekeningService.findAllRekening(pageable);
            return ResponseEntity.ok(new ApiResponse<>(200, rekeningPage, "sukses"));
        } catch (Exception e) {
            throw new CustomException("Data tidak ditemukan");
        }
    }

    @GetMapping("/rekening/{id}")
    public ResponseEntity<?> getRekeningById(@PathVariable Long id) {
        try {
            Rekening rekening = rekeningService.findRekeningById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Rekening not found with id: " + id));
            return ResponseEntity.ok(new ApiResponse<>(200, rekening, "sukses"));

        } catch (Exception e) {
            throw new CustomException("Data tidak ditemukan");
        }
    }

    @DeleteMapping("/rekening/delete")
    public ResponseEntity<?> deleteRekeningById(@RequestBody RekeningDeleteDTO rekeningDeleteDTO) {
        try {
            rekeningService.deleteRekeningById(rekeningDeleteDTO.getId());
            return ResponseEntity.ok(new ApiResponse<>(200, "Sukses", "sukses"));
        } catch (Exception e) {
            throw new CustomException("Data tidak ditemukan");
        }
    }

}
