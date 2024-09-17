package com.shivam.talk.auth;


import com.shivam.talk.dto.PhoneDTO;
import com.shivam.talk.dto.SignupDTO;
import com.shivam.talk.dto.VerifyOtpDTO;
import com.shivam.talk.entity.Otp;
import com.shivam.talk.entity.User;
import com.shivam.talk.service.OtpService;
import com.shivam.talk.service.UserService;
import com.shivam.talk.util.JWTUtil;
import com.shivam.talk.util.OtpUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



//TODO add status code and proper responses and also return jwt from verify otp



@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    public UserService  userService;


    @Autowired
    public OtpService  otpService;

    @Autowired
    public JWTUtil jwtUtil;



    @PostMapping("/generateotp")
    public String generateOtp(@RequestBody PhoneDTO phone) {


        Optional<User> user = userService.findByNumber(phone.getPhoneNumber());

        if (user.isPresent()) {

            String otp = OtpUtilService.generateOtp();

            System.out.println(otp);

            Otp otpObj = new Otp();
            otpObj.setOtp(otp);
            otpObj.setNumber(phone.getPhoneNumber());

            otpService.saveOtp(otpObj);

            return "OTP Geenrated"  + otp;

        }

        return "User Does Not Exist";
    }


    @PostMapping("/verifyotp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpDTO verifyOtp) {

        Optional<User> user = userService.findByNumber(verifyOtp.getPhoneNumber());

        if (user.isPresent()) {

            Optional<Otp> otp  = otpService.findByNumber(verifyOtp.getPhoneNumber());

            if (otp.isPresent()) {

                String userOtp = verifyOtp.getOtp();

                String ServerOtp = otp.get().getOtp();

                if(userOtp.equals(ServerOtp)){

                    Long otpId =  1l *  otp.get().getId();
                    otpService.deleteOtp(otpId);

                    String jwt = jwtUtil.generateToken(user.get().getUsername());

                    return ResponseEntity.ok(jwt);
                }


                return ResponseEntity.status(401).body("Wrong OTP");
            }

            return  ResponseEntity.status(400).body("Send OTP First");

        }




        return ResponseEntity.status(404).body("User Does Not Exist");
    }


    @PostMapping("/signup")
    public String signup(@RequestBody SignupDTO user) {

        Optional<User> foundUser = userService.findByNumber(user.getPhoneNumber());

        if (foundUser.isPresent()) {
            return null;
        }
        else {

            User newUser = new User();

            newUser.setNumber(user.getPhoneNumber());
            newUser.setName(user.getName());

            userService.save(newUser);

            System.out.println("New User Created \n"+ newUser);

            return "User Created";

        }



    }

}
