package com.hrd.karyawan.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrd.karyawan.dto.KaryawanDTO;
import com.hrd.karyawan.dto.KaryawanIdDTO;
import com.hrd.karyawan.dto.KaryawanUpdateDTO;
import com.hrd.karyawan.entities.Karyawan;
import com.hrd.karyawan.exception.CustomException;
import com.hrd.karyawan.services.KaryawanService;

@RestController
@RequestMapping("/v1/idstar")
public class KaryawanController {

    @Autowired
    private KaryawanService karyawanService;

    @PostMapping("/karyawan/save")
    public ResponseEntity<?> saveKaryawan(@Valid @RequestBody KaryawanDTO karyawanDTO) {
        try{
            Karyawan karyawan = karyawanService.saveKaryawan(karyawanDTO);
            return ResponseEntity.ok(new ApiResponse<>(200, karyawan, "sukses"));    
        }catch(Exception e){
            throw new CustomException("Gagal proses penyimpanan");
        }
    }

    @PutMapping("/karyawan/update")
    public ResponseEntity<?> updateKaryawan(@Valid @RequestBody KaryawanUpdateDTO karyawanUpdateDTO) {
        try{
            Karyawan karyawan = karyawanService.updateKaryawan(karyawanUpdateDTO);
            return ResponseEntity.ok(new ApiResponse<>(200, karyawan, "sukses"));
        }catch(Exception e){
            throw new CustomException("Gagal proses update date");
        }
    }

    @GetMapping("/karyawan/list")
    public ResponseEntity<?> getKaryawanList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try{
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
            Page<Karyawan> karyawanPage = karyawanService.getKaryawanList(pageable);
            ApiResponse<Page<Karyawan>> apiResponse = new ApiResponse<>(200, karyawanPage, "sukses");
            return ResponseEntity.ok(apiResponse);    
        }catch(Exception e){
            throw new CustomException("Data tidak ditemukan");
        }
    }

    @GetMapping("/karyawan/{id}")
    public ResponseEntity<ApiResponse<Karyawan>> getKaryawanById(@PathVariable(value = "id") Long id) {
        try{
            Karyawan karyawan = karyawanService.findById(id);
            return ResponseEntity.ok(new ApiResponse<>(200, karyawan, "sukses"));    
        }catch(Exception e){
            throw new CustomException("Data tidak ditemukan");
        }
    }

    @DeleteMapping("/karyawan/delete")
    public ResponseEntity<ApiResponse<String>> deleteKaryawan(@RequestBody KaryawanIdDTO karyawanIdDTO) {
        try{
            karyawanService.deleteById(karyawanIdDTO.getId());
            return ResponseEntity.ok(new ApiResponse<>(200, "Sukses", "sukses"));
        }catch(Exception e){
            throw new CustomException("Gagal proses hapus data");
        }
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<String>> handleCustomException(CustomException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(500, ex.getMessage(), "gagal"));
    }

}
