package com.hrd.karyawan.controllers;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int code;
    private T data;
    private String status;

    public ApiResponse(int code,  T data, String status) {
        this.code = code;
        this.data = data;
        this.status = status;
    }

}
