package com.blogspot.ranganathankm.sb.ws.user.without.stomp;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 *
 * @author ranga
 */
@Service
public class BroadcastService {
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public BroadcastService() {
    }
    
    public void addToSession(WebSocketSession session) {
        sessions.add(session);
    }
    
    public void broadcastMessage(String message) throws IOException {
        for (WebSocketSession session : sessions) {
            session.sendMessage(new TextMessage(message));
        }
    }
    
    public void targetedMessage(String user, String message) throws IOException {
        for (WebSocketSession session : sessions) {
            if(session.getPrincipal().getName().equals(user)) {
                session.sendMessage(new TextMessage(message));
                break;
            }
        }
    }
}
