package com.example.nettysocketio.domains.message.controller;

import com.example.nettysocketio.domains.message.dto.DestinationRequest;
import com.example.nettysocketio.domains.message.dto.SendRequest;
import com.example.nettysocketio.domains.message.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.example.nettysocketio.domains.message.controller
 * fileName       : ChatMessageController
 * author         : Yeong-Huns
 * date           : 2025-02-03
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-03        Yeong-Huns       최초 생성
 */
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @GetMapping
    public ResponseEntity<List<SendRequest>> getAllMessages(@RequestParam String destination) {
        return ResponseEntity.ok(chatMessageService.findByDestination(destination));
    }
}
