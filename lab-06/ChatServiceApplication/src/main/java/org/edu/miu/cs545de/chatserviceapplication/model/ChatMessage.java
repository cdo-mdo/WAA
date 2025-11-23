package org.edu.miu.cs545de.chatserviceapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "chatMessages")
public class ChatMessage {

    @Id
    private String id;

    private String content;

    private Instant createdAt;

    public ChatMessage() {

    }

    public ChatMessage(String id, String content, Instant createdAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}
