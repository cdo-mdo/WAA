package org.edu.miu.cs545de.chatserviceapplication.service;

import lombok.RequiredArgsConstructor;
import org.edu.miu.cs545de.chatserviceapplication.model.ChatMessage;
import org.edu.miu.cs545de.chatserviceapplication.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository repository;

    // Requirement: returns a Flux of chat messages from MongoDB
    public Flux<ChatMessage> getChatMessages() {
        return repository.findBy();   // tailable stream
    }

    public Mono<ChatMessage> addMessage(String content) {
        ChatMessage msg = new ChatMessage(null, content, Instant.now());
        return repository.save(msg);
    }

    public ChatService() {

    }

    public ChatService(ChatMessageRepository repository) {
        this.repository = repository;
    }

}
