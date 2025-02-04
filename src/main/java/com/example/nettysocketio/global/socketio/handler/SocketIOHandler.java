package com.example.nettysocketio.global.socketio.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.example.nettysocketio.domains.message.dto.SendRequest;
import com.example.nettysocketio.domains.message.service.ChatMessageService;
import com.example.nettysocketio.global.error.exception.NotFoundException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * packageName    : com.example.nettysocketio.global.socketio.handler
 * fileName       : SocketIOHandler
 * author         : Yeong-Huns
 * date           : 2025-02-03
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-03        Yeong-Huns       최초 생성
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class SocketIOHandler {
    private final SocketIOServer socketIOServer;
    private final ChatMessageService chatMessageService;

    @PostConstruct
    public void runServer() {
        socketIOServer.addConnectListener(client -> {
            log.info("클라이언트 연결됨. {}", client.getSessionId());
        });

        socketIOServer.addDisconnectListener(client -> {
            log.info("클라이언트 연결 해제됨. {}", client.getSessionId());
        });

        socketIOServer.addEventListener("join", String.class, (client, room, ackRequest) -> {
            client.joinRoom(room);
            List<SendRequest> previousMessage = chatMessageService.findByDestination((room));
            client.sendEvent("previousMessage", previousMessage);
            log.info("클라이언드 {}가 {} 방에 참여함. ackRequest: {}", client.getSessionId(), room, ackRequest);
        });

        socketIOServer.addEventListener("leave", String.class, (client, room, ackRequest) -> {
            client.leaveRoom(room);
            log.info("클라이언드 {}가 {} 방에서 떠남. ackRequest: {}", client.getSessionId(), room, ackRequest);
        });

        socketIOServer.addEventListener("privateMessage", SendRequest.class, (client, data, ackRequest) -> {
            UUID destinationId = UUID.fromString(data.destination());
            SocketIOClient destination = socketIOServer.getClient(destinationId);
            if (destination != null) {
                chatMessageService.saveMessage(data);
                destination.sendEvent("privateMessage", data);
                log.info("개인 메세지 전송: {} -> {}, 내용: {}", client.getSessionId(), destinationId, data.message());
            } else {
                throw new NotFoundException("존재하지 않는 사용자입니다.");
            }

        });

        socketIOServer.addEventListener("groupMessage", SendRequest.class, (client, data, ackRequest) -> {
            chatMessageService.saveMessage(data);
            String room = data.destination();
            socketIOServer.getRoomOperations(room).sendEvent("groupMessage", data);
            log.info("그룹 메세지 전송: [방 이름: {}], 발신자: {},  내용: {}", room, data.sender(), data.message());
        });

        socketIOServer.start();
    }

    @PreDestroy
    public void stopServer() {
        if (socketIOServer != null) {
            socketIOServer.stop();
        }
    }
}
