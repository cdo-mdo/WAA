package org.edu.miu.cs545de.chatserviceapplication.controller;

import lombok.RequiredArgsConstructor;
import org.edu.miu.cs545de.chatserviceapplication.model.ChatMessage;
import org.edu.miu.cs545de.chatserviceapplication.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private ChatService chatService;

    // Client can call this and receive a continuous stream of messages
    @GetMapping(value = "/messages", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatMessage> streamMessages() {
        return chatService.getChatMessages();
    }
}


