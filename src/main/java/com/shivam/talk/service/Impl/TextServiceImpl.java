package com.shivam.talk.service.Impl;

import com.shivam.talk.entity.Text;
import com.shivam.talk.repository.TextRepository;
import com.shivam.talk.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;



@Service
public class TextServiceImpl implements TextService {

    @Autowired
    private TextRepository textRepository;

    @Override
    public List<Text> findAllByReciever(String reciever) {
        return   textRepository.findAllByReviever(reciever);

    }

    @Override
    public List<Text> findAllByRevieverAndReceivedTime(String reciever, Date receivedTime) {
        return textRepository.findAllByRevieverAndReceivedTime(reciever, receivedTime);
    }


    @Override
    public Text save(Text text) {

        textRepository.save(text);
        return text;
    }

    @Override
    public boolean delete(Text text) {

        textRepository.delete(text);

        return true;
    }
}
