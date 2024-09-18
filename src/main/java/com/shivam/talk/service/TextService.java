package com.shivam.talk.service;


import com.shivam.talk.entity.Text;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface TextService {

    public List<Text> findAllByReciever(String reciever);

    public List<Text> findAllByRevieverAndReceivedTime(String reciever, Date receivedTime);

    public Text findById(Long id);

    public Text save(Text text);

    public boolean delete(Text text);

}
