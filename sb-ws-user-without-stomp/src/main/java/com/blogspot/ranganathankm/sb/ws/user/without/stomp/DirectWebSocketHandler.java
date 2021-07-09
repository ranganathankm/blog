package com.blogspot.ranganathankm.sb.ws.user.without.stomp;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 *
 * @author ranga
 */
public class DirectWebSocketHandler extends TextWebSocketHandler {
    private BroadcastService broadcastService;

    public DirectWebSocketHandler(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        broadcastService.addToSession(session);
    }
    
}
