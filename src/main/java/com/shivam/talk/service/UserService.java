package com.shivam.talk.service;


import com.shivam.talk.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {


    User save(User user);


    Optional<User> findByNumber(String number);

}
