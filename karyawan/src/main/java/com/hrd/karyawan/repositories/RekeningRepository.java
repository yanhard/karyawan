package com.hrd.karyawan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrd.karyawan.entities.Rekening;

@Repository
public interface RekeningRepository extends JpaRepository<Rekening, Long> {
}
