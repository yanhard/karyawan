package com.hrd.karyawan.controllers;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int code;
    private T data;

    public ApiResponse(int code,  T data) {
        this.code = code;
        this.data = data;
    }

}
