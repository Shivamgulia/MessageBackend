package com.shivam.talk.service.Impl;

import com.shivam.talk.entity.User;
import com.shivam.talk.repository.UserRepository;
import com.shivam.talk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    public UserRepository userRepository;


    @Override
    public User save(User user) {

        User savedUser  = userRepository.save(user);

        return savedUser;
    }

    @Override
    public Optional<User> findByNumber(String number) {
        return userRepository.findByNumber(number);
    }


}
