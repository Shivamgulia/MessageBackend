package com.shivam.talk.util;

import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class OtpUtilService {

    public static String generateOtp() {

        Random rand = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            otp.append(rand.nextInt(10));
        }

        return otp.toString();
    }

}
