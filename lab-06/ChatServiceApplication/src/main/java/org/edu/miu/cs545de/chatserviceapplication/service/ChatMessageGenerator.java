package org.edu.miu.cs545de.chatserviceapplication.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class ChatMessageGenerator {

    private static final Logger log = LoggerFactory.getLogger(ChatMessageGenerator.class);

    @Autowired
    private ChatService chatService;

    // Requirement: add every 3 seconds a new message to the database
    @PostConstruct
    public void startGeneratingMessages() {
        Flux.interval(Duration.ofSeconds(3))
                .flatMap(i -> chatService.addMessage("Auto message #" + i))
                .doOnNext(m -> log.info("Inserted message: {}", m))
                .subscribe();  // fire-and-forget for demo
    }
}

