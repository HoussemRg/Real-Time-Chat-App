package com.example.real_time_chat_app.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        return chatMessage;
    }


    //establish a connexion between a user and a websocket
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
            )
    {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        // Create a custom JOIN message with a special type for the client to display
        ChatMessage joinMessage = new ChatMessage();
        joinMessage.setSender(chatMessage.getSender());
        joinMessage.setContent(chatMessage.getSender() + " is joining the conversation");
        joinMessage.setMessageType(MessageType.JOIN); // Type "JOIN" to indicate it's a user joining

        return joinMessage;

    }
}
