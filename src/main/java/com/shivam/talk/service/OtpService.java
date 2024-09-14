package com.shivam.talk.service;

import com.shivam.talk.entity.Otp;

import java.util.Optional;

public interface OtpService {


    public Otp saveOtp(Otp otp);

    public Optional<Otp> findByNumber (String number);

    public String deleteOtp(Long  id);

}
