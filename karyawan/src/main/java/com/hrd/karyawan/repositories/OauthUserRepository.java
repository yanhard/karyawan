package com.hrd.karyawan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrd.karyawan.entities.OauthUser;

@Repository
public interface OauthUserRepository extends JpaRepository<OauthUser, Long> {

    OauthUser findByUsername(String username);    
}
