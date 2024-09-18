package com.shivam.talk.controller.chat;


import com.shivam.talk.entity.Message;
import com.shivam.talk.entity.Status;
import com.shivam.talk.entity.Text;
import com.shivam.talk.service.TextService;
import com.shivam.talk.service.UserService;
import com.shivam.talk.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {


    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private TextService textService;



    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
//        System.out.println("Received message: " + message + " initilizing connection");
//        return message;
        return null;
    }

    @MessageMapping("/private-message")
    public List<Message> recMessage(@Payload Message message){

        if(validateUser(message.getAuth(), message.getSenderName())) {

            if(message.getStatus() == Status.JOIN) {
                //TODO getting all the messages that are not yet sent to user in absence

                List<Text> unrecievedTexts = textService.findAllByRevieverAndReceivedTime(message.getReceiverName(), null);
                List<Message> unrecievedMessages = new ArrayList<>();
                for(Text unrecievedText : unrecievedTexts) {
                    Message temp = new Message();
                    temp.setMessage(unrecievedText.getText());
                    temp.setSenderName(unrecievedText.getSender());
                    temp.setReceiverName(unrecievedText.getReviever());
                    temp.setStatus(Status.MESSAGE);
                    temp.setDate(unrecievedText.getReceivedTime());
                }
                return unrecievedMessages;


            }

            if(message.getStatus() == Status.RECIPT) {
                //TODO updating recieved as server

            }

            simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
            System.out.println(simpMessagingTemplate.getUserDestinationPrefix());
            System.out.println(message.toString());
            List<Message> messages = new ArrayList<>();
            messages.add(message);
            return messages;
        }
        return null;
    }


    boolean validateUser(String auth, String senderName) {

        String username = null;
        String jwt = null;
        if (auth != null && auth.startsWith("Bearer ")) {
            jwt = auth.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        if (username != null) {
            UserDetails userDetails = userService.findByUsername(username);
            if (jwtUtil.validateToken(jwt)) {
                if(userDetails.getUsername().equals(senderName)) {
                    return true;
                }
            }
        }

        System.out.println("Failed Message");
        return false;
    }



}
