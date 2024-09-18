package com.shivam.talk.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {

    private long id;
    private String senderName;
    private String receiverName;
    private String message;
    private String auth;
    private Date date;
    private Status status;
}
