package com.example.nettysocketio.domains.message.dto;

import com.example.nettysocketio.domains.message.domain.ChatMessage;

/**
 * packageName    : com.example.nettysocketio.domains.message.dto
 * fileName       : SaveRequest
 * author         : Yeong-Huns
 * date           : 2025-02-03
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-03        Yeong-Huns       최초 생성
 */
public record SendRequest(String sender, String destination, String message) {
    public ChatMessage toEntity(){
        return new ChatMessage(sender, destination, message);
    }
    public static SendRequest fromChatMessage(ChatMessage chatMessage){
        return new SendRequest(chatMessage.getSender(), chatMessage.getDestination(), chatMessage.getMessage());
    }
}
