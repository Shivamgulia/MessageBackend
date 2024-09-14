package com.shivam.talk.service.Impl;

import com.shivam.talk.entity.Otp;
import com.shivam.talk.repository.OtpRepository;
import com.shivam.talk.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OtpServiceImpl implements OtpService {


    @Autowired
    private OtpRepository otpRepository;


    @Override
    public Otp saveOtp(Otp otp) {

        return otpRepository.save(otp);
    }

    @Override
    public Optional<Otp> findByNumber(String number) {

        return  otpRepository.findOtpByNumber(number);
    }

    @Override
    public String deleteOtp(Long id) {

        otpRepository.deleteById(id);
        return "";
    }
}
