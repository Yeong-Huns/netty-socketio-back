package com.example.nettysocketio.domains.message.repository;

import com.example.nettysocketio.domains.message.domain.ChatMessage;
import com.example.nettysocketio.domains.message.dto.SendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.example.nettysocketio.domains.message.repository
 * fileName       : ChatMessageRepository
 * author         : Yeong-Huns
 * date           : 2025-02-03
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-03        Yeong-Huns       최초 생성
 */
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByDestination(String destination);
}
