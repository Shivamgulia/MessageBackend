package com.shivam.talk.controller.chat;


import com.shivam.talk.entity.Message;
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

@Controller
public class ChatController {


    @Autowired
    private JWTUtil jwtUtil;


    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
//        System.out.println("Received message: " + message + " initilizing connection");
//        return message;
        return null;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){



        if(validateUser(message.getAuth(), message.getSenderName())) {


        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);

        System.out.println(message.toString());
        return message;
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
