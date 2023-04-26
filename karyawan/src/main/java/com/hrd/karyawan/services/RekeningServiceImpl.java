package com.hrd.karyawan.services;

import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hrd.karyawan.dto.RekeningDTO;
import com.hrd.karyawan.entities.Karyawan;
import com.hrd.karyawan.entities.Rekening;
import com.hrd.karyawan.repositories.KaryawanRepository;
import com.hrd.karyawan.repositories.RekeningRepository;

@Service
public class RekeningServiceImpl implements RekeningService {

    @Autowired
    private RekeningRepository rekeningRepository;

    @Autowired
    private KaryawanRepository karyawanRepository;

    @Override
    public Rekening saveRekening(RekeningDTO rekeningDTO) {
        Rekening rekening = new Rekening();
        rekening.setNama(rekeningDTO.getNama());
        rekening.setJenis(rekeningDTO.getJenis());
        rekening.setRekening(rekeningDTO.getRekening());
        rekening.setAlamat(rekeningDTO.getAlamat());

        Assert.notNull(rekeningDTO.getKaryawan().getId(), "Karyawan ID must not be null");
        Karyawan karyawan = karyawanRepository.findById(rekeningDTO.getKaryawan().getId())
            .orElseThrow();
            //Hibernate.initialize(karyawan.getRekeningList());
        rekening.setKaryawan(karyawan);

        return rekeningRepository.save(rekening);
    }

    @Override
    public Rekening updateRekening(Long id, RekeningDTO rekeningDTO) {
    Rekening rekening = rekeningRepository.findById(id)
    .orElseThrow(() -> new IllegalArgumentException("Rekening not found with id: " + id));

    rekening.setNama(rekeningDTO.getNama());
    rekening.setJenis(rekeningDTO.getJenis());
    rekening.setRekening(rekeningDTO.getRekening());
    rekening.setAlamat(rekeningDTO.getAlamat());

    Assert.notNull(rekeningDTO.getKaryawan().getId(), "Karyawan ID must not be null");
    Karyawan karyawan = karyawanRepository.findById(rekeningDTO.getKaryawan().getId())
        .orElseThrow(() -> new IllegalArgumentException("Karyawan not found with id: " + rekeningDTO.getKaryawan().getId()));
    rekening.setKaryawan(karyawan);

    return rekeningRepository.save(rekening);
    }

    @Override
    public Page<Rekening> findAllRekening(Pageable pageable) {
        return rekeningRepository.findAll(pageable);
    }

    @Override
    public Optional<Rekening> findRekeningById(Long id) {
        return rekeningRepository.findById(id);
    }
    
    @Override
    public void deleteRekeningById(Long id) {
        rekeningRepository.deleteById(id);
    }

}

