package org.edu.miu.cs545de.chatserviceapplication.config;

import org.edu.miu.cs545de.chatserviceapplication.model.ChatMessage;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;

@Configuration
public class MongoConfig {

    @Bean
    CommandLineRunner initCappedCollection(ReactiveMongoTemplate template) {
        return args -> {
            template.collectionExists(ChatMessage.class)
                    .flatMap(exists -> {
                        if (exists) {
                            return Mono.empty();
                        }
                        // Create capped collection for tailable cursor
                        CollectionOptions options = CollectionOptions.empty()
                                .capped()
                                .size(1_000_000L)      // 1MB
                                .maxDocuments(1000L);  // up to 1000 docs
                        return template.createCollection(ChatMessage.class, options).then();
                    })
                    .block(); // only at startup
        };
    }
}

