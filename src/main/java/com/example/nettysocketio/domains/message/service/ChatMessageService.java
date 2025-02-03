package com.example.nettysocketio.domains.message.service;

import com.example.nettysocketio.domains.message.domain.ChatMessage;
import com.example.nettysocketio.domains.message.dto.DestinationRequest;
import com.example.nettysocketio.domains.message.dto.SendRequest;
import com.example.nettysocketio.domains.message.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.example.nettysocketio.domains.message.service
 * fileName       : ChatMessageService
 * author         : Yeong-Huns
 * date           : 2025-02-03
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-03        Yeong-Huns       최초 생성
 */
@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public List<SendRequest> findByDestination(String destination) {
        return chatMessageRepository.findByDestination(destination).orElseThrow(() -> new IllegalArgumentException("해당하는 방이 없습니다."));
    }
}
