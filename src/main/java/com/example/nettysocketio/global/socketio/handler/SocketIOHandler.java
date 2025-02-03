package com.example.nettysocketio.global.socketio.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.example.nettysocketio.domains.message.dto.SendRequest;
import com.example.nettysocketio.domains.message.repository.ChatMessageRepository;
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
    private final ChatMessageRepository chatMessageRepository;

    @PostConstruct
    public void runServer(){
        socketIOServer.addConnectListener(client -> {
            log.info("클라이언트 연결됨. {}", client.getSessionId());
        });

        socketIOServer.addDisconnectListener(client -> {
            log.info("클라이언트 연결 해제됨. {}", client.getSessionId());
        });

        socketIOServer.addEventListener("join", String.class, (client, room, ackRequest) -> {
            client.joinRoom(room);
            List<SendRequest> previousMessage = chatMessageRepository.findByDestination((room)).orElseThrow(() -> new IllegalArgumentException("해당하는 방이 없습니다."));
            client.sendEvent("previousMessage", previousMessage);
            log.info("클라이언드 {}가 {} 방에 참여함. ackRequest: {}", client.getSessionId(), room, ackRequest);
        });

        socketIOServer.addEventListener("leave", String.class, (client, room, ackRequest) -> {
            client.leaveRoom(room);
            log.info("클라이언드 {}가 {} 방에서 떠남. ackRequest: {}", client.getSessionId(), room, ackRequest);
        });

        socketIOServer.addEventListener("privateMessage", SendRequest.class, (client, data, ackRequest) -> {
            try{
                UUID destinationId = UUID.fromString(data.destination());
                SocketIOClient destination = socketIOServer.getClient(destinationId);
                if(destination != null){
                    chatMessageRepository.save(data.toEntity());
                    destination.sendEvent("privateMessage", data);
                    log.info("개인 메세지 전송: {} -> {}, 내용: {}", client.getSessionId(), destinationId, data.message());
                } else {
                    log.info("존재하지않는 destinationId: {}", data.destination());
                }
            }catch(IllegalStateException e){
                log.info("잘못된 수신자 : {} 에러 내용 : {} ", data.destination(), e.getMessage());
            }
        });

        socketIOServer.addEventListener("groupMessage", SendRequest.class, (client, data, ackRequest) -> {
           chatMessageRepository.save(data.toEntity());
           String room = data.destination();
           socketIOServer.getRoomOperations(room).sendEvent("groupMessage", data);
            log.info("그룹 메세지 전송: [방 이름: {}], 발신자: {},  내용: {}", room , data.sender(), data.message());
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
