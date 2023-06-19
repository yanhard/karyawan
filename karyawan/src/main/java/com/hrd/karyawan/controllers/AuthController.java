package com.hrd.karyawan.controllers;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hrd.karyawan.dto.RegisterRequest;
import com.hrd.karyawan.entities.OauthUser;
import com.hrd.karyawan.exception.DuplicateEmailException;
import com.hrd.karyawan.repositories.OauthUserRepository;
import com.hrd.karyawan.services.OauthUserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final long OTP_EXPIRATION_MINUTES = 30;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OauthUserRepository oauthUserRepository;

    @PostMapping("/user-register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {

        // Create new user
        OauthUser user = new OauthUser();
        user.setDomicile(registerRequest.getDomicile());
        user.setFullname(registerRequest.getFullname());
        user.setGender(registerRequest.getGender());
        user.setPhone_number(registerRequest.getPhone_number());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEnabled(false);
        user.setOtp(generateOtp());
        user.setOtpExpiredDate(LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES));
        user.setStatus("P");

        // Cek email
        try {
            OauthUser existingUser = oauthUserRepository.findByUsername(user.getUsername());
            if(existingUser != null){
                throw new DuplicateEmailException("Email is already in use");
            }
        } catch (Exception ise) {
            throw new ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR, "Terjadi kesalahan pada server", ise);
        }        
        
        // Save user to database
        oauthUserRepository.save(user);

        // Send OTP to user email
        sendOtpEmail(user);

        // Return response
        return ResponseEntity.ok("Registration successful. Please check your email to activate your account.");
    }

    @Autowired
    OauthUserService oauthUserService;
    
    @PostMapping("/user-register/register-confirm-otp/{otp}")
    public ResponseEntity<?> validateOTP(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String otp = body.get("otp");
        
        boolean isValid = oauthUserService.validateOTP(username, otp);

        if (isValid) {
            return ResponseEntity.ok("OTP is valid");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
        }
    }
    
    private String generateOtp() {
        // Generate a random 6-digit OTP
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    private void sendOtpEmail(OauthUser user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getUsername());
        message.setSubject("Registration Confirmation");
        message.setText("Your OTP is: " + user.getOtp());
        mailSender.send(message);
    }

}
