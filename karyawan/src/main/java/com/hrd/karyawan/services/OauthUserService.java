package com.hrd.karyawan.services;

public interface OauthUserService {
    boolean validateOTP(String username, String otp);
}
