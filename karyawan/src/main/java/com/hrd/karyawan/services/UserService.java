package com.hrd.karyawan.services;

import com.hrd.karyawan.dto.RegisterRequest;

public interface UserService {
    boolean registerUser(RegisterRequest registerRequest);
}
