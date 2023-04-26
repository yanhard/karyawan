package com.hrd.karyawan.dto;

import lombok.Data;

@Data
public class RekeningDTO {
    private String nama;
    private String jenis;
    private String rekening;
    private String alamat;
    //private Long karyawanId;
    private KaryawanIdWrapper karyawan;

    // Getters and setters

    public static class KaryawanIdWrapper {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }    
}

