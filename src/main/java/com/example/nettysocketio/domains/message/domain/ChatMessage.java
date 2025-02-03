package com.example.nettysocketio.domains.message.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 * packageName    : com.example.nettysocketio.domains.message.domain
 * fileName       : ChatMessage
 * author         : Yeong-Huns
 * date           : 2025-02-03
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-03        Yeong-Huns       최초 생성
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender;
    private String destination;
    private String message;

    @Builder
    public ChatMessage(String sender, String destination, String message) {
        this.sender = sender;
        this.destination = destination;
        this.message = message;
    }


}
