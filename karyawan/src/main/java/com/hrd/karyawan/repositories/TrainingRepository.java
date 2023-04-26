package com.hrd.karyawan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrd.karyawan.entities.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
}

