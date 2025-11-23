package org.edu.miu.cs545de.chatservice.service;

import jakarta.annotation.PostConstruct;
import org.edu.miu.cs545de.chatservice.model.ChatMessage;
import org.edu.miu.cs545de.chatservice.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Service
public class ChatService {

    private final ChatMessageRepository repository;

    public ChatService(ChatMessageRepository repository) {
        this.repository = repository;
    }

    // 1) Return Flux of chat messages from MongoDB
    public Flux<ChatMessage> getChatMessages() {
        // sort by time (optional)
        return repository.findAll()
                .sort((m1, m2) -> m1.getCreatedAt().compareTo(m2.getCreatedAt()));
    }

    // 2) Every 3 seconds, insert a new message
    @PostConstruct
    public void startAutoInsert() {
        Flux.interval(Duration.ofSeconds(3))
                .flatMap(this::insertMessage)
                .subscribe();   // start the reactive pipeline
    }

    private Mono<ChatMessage> insertMessage(Long index) {
        ChatMessage msg = new ChatMessage(
                "Auto message #" + index,
                Instant.now()
        );
        return repository.save(msg);
    }
}
