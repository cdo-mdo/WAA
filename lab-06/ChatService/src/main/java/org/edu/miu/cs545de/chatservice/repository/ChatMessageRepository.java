package org.edu.miu.cs545de.chatservice.repository;

import org.edu.miu.cs545de.chatservice.model.ChatMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ChatMessageRepository
        extends ReactiveMongoRepository<ChatMessage, String> {
}
