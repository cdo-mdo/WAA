package org.edu.miu.cs545de.chatserviceapplication.repository;

import org.edu.miu.cs545de.chatserviceapplication.model.ChatMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.mongodb.repository.Query;
import reactor.core.publisher.Flux;

public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessage, String> {

    // Keeps the cursor open and emits new messages as they are inserted
    @Tailable
    @Query("{}")
    Flux<ChatMessage> findBy();
}
