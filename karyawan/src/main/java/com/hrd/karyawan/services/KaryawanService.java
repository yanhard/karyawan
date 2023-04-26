package com.hrd.karyawan.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hrd.karyawan.dto.KaryawanDTO;
import com.hrd.karyawan.dto.KaryawanUpdateDTO;
import com.hrd.karyawan.entities.Karyawan;

public interface KaryawanService {
    Karyawan saveKaryawan(KaryawanDTO karyawanDTO);
    Karyawan updateKaryawan(KaryawanUpdateDTO karyawanUpdateDTO);
    Page<Karyawan> getKaryawanList(Pageable pageable);
    Karyawan findById(Long id);
    void deleteById(Long id);
}

