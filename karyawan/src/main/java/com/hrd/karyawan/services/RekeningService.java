package com.hrd.karyawan.services;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.hrd.karyawan.dto.RekeningDTO;
import com.hrd.karyawan.entities.Rekening;

public interface RekeningService {
    Rekening saveRekening(RekeningDTO rekeningDTO);
    Rekening updateRekening(RekeningDTO rekeningDTO);
    Page<Rekening> findAllRekening(Pageable pageable);
    Optional<Rekening> findRekeningById(Long id);
    void deleteRekeningById(Long id);
}

