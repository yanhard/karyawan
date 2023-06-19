package com.hrd.karyawan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hrd.karyawan.entities.OauthUser;
import com.hrd.karyawan.repositories.OauthUserRepository;

@Service
public class OauthUserServiceImpl implements OauthUserService {

    @Autowired
    OauthUserRepository oauthUserRepository;

    public boolean validateOTP(String username, String otp) {
        // Ambil OTP dari database atau tempat penyimpanan lainnya berdasarkan user yang
        // String savedOTP = getOTPOfUser(username);
        OauthUser user = getOTPOfUser(username);

        // Bandingkan OTP dari request dengan OTP yang tersimpan
        // return savedOTP.equals(otp);
        if (user.getOtp().equals(otp)) {
            user.setStatus("A");
            oauthUserRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    private OauthUser getOTPOfUser(String username) {
        // Cari OTP dari database.
        OauthUser user = oauthUserRepository.findByUsername(username);

        // Kembali ke OTP jika ditemukan.
        if (user != null) {
            return user;
        } else {
            // Jika tidak ada OTP, kembalikan null atau handle situasi ini dengan cara yang
            // lebih sesuai.
            return null;
        }
    }

    private String getOTPOfLoggedInUser() {
        // Dapatkan akses ke pengguna yang saat ini login.
        OauthUser loggedInUser = getLoggedInUser();

        // Cari OTP dari database.
        OauthUser otp = oauthUserRepository.findByUsername(loggedInUser.getUsername());

        // Kembali ke OTP jika ditemukan.
        if (otp != null) {
            return otp.getOtp();
        } else {
            // Jika tidak ada OTP, kembalikan null atau handle situasi ini dengan cara yang
            // lebih sesuai.
            return null;
        }
    }

    private OauthUser getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return oauthUserRepository.findByUsername(username);
        } else {
            return null;
        }
    }

}
