package com.hrd.karyawan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrd.karyawan.dto.DetailKaryawanDTO;
import com.hrd.karyawan.dto.KaryawanDTO;
import com.hrd.karyawan.dto.KaryawanUpdateDTO;
import com.hrd.karyawan.entities.DetailKaryawan;
import com.hrd.karyawan.entities.Karyawan;
import com.hrd.karyawan.repositories.KaryawanRepository;

@Service
public class KaryawanServiceImpl implements KaryawanService {

    @Autowired
    private KaryawanRepository karyawanRepository;

    @Override
    public Karyawan saveKaryawan(KaryawanDTO karyawanDTO) {
        Karyawan karyawan = new Karyawan();
        // Populate Karyawan object with KaryawanDTO data
        karyawan.setNama(karyawanDTO.getNama());
        karyawan.setDob(karyawanDTO.getDob());
        karyawan.setStatus(karyawanDTO.getStatus());
        karyawan.setAlamat(karyawanDTO.getAlamat());

        // Populate DetailKaryawan object with DetailKaryawanDTO data
        DetailKaryawan detailKaryawan = new DetailKaryawan();
        DetailKaryawanDTO detailKaryawanDTO = karyawanDTO.getDetailKaryawan();
        detailKaryawan.setNik(detailKaryawanDTO.getNik());
        detailKaryawan.setNpwp(detailKaryawanDTO.getNpwp());

        // Set the DetailKaryawan object to the Karyawan object
        karyawan.setDetailKaryawan(detailKaryawan);

        return karyawanRepository.save(karyawan);
    }

    @Transactional
    @Override
    public Karyawan updateKaryawan(KaryawanUpdateDTO karyawanUpdateDTO) {
        // Find the Karyawan entity by id
        Karyawan karyawan = karyawanRepository.findById(karyawanUpdateDTO.getId()).orElseThrow();

        // Update the Karyawan entity with KaryawanUpdateDTO data
        karyawan.setNama(karyawanUpdateDTO.getNama());
        karyawan.setDob(karyawanUpdateDTO.getDob());
        karyawan.setStatus(karyawanUpdateDTO.getStatus());
        karyawan.setAlamat(karyawanUpdateDTO.getAlamat());

        // Update the DetailKaryawan entity with DetailKaryawanDTO data
        DetailKaryawan detailKaryawan = karyawan.getDetailKaryawan();
        DetailKaryawanDTO detailKaryawanDTO = karyawanUpdateDTO.getDetailKaryawan();
        detailKaryawan.setNik(detailKaryawanDTO.getNik());
        detailKaryawan.setNpwp(detailKaryawanDTO.getNpwp());

        // Set the updated DetailKaryawan entity to the Karyawan entity
        karyawan.setDetailKaryawan(detailKaryawan);

        // Save the updated Karyawan entity
        return karyawanRepository.save(karyawan);
    }

    @Override
    public Page<Karyawan> getKaryawanList(Pageable pageable) {
        return karyawanRepository.findAll(pageable);
    }

    @Override
    public Karyawan findById(Long id) {
    return karyawanRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
    Karyawan karyawan = karyawanRepository.findById(id).orElseThrow();
    karyawanRepository.delete(karyawan);
}


}

