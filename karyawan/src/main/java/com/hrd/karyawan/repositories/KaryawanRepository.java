package com.hrd.karyawan.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrd.karyawan.entities.Karyawan;

@Repository
public interface KaryawanRepository extends JpaRepository<Karyawan, Long> {
    Page<Karyawan> findAll(Pageable pageable);
}

