package com.shivam.talk.repository;

import com.shivam.talk.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {


    Optional<Otp> findOtpByNumber(String number);

}
