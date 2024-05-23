package pl.piotrowskl._001.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import pl.piotrowskl._001.model.User;
import pl.piotrowskl._001.service.UserDetailsServiceImpl;
import pl.piotrowskl._001.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class WebSocketHandler extends TextWebSocketHandler {
    private static Set<WebSocketSession> sessions = new HashSet<>();
    private static int clickCounter = 0;
    private final UserService userService;

    public WebSocketHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
//        session.sendMessage(new TextMessage("Counter: " + clickCounter));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            String payload = message.getPayload();
            if (payload.equals("increment")){
                clickCounter++;
                for (WebSocketSession s : sessions){
                    s.sendMessage(new TextMessage("Counter: " + clickCounter));
                }
            }else {
                for (WebSocketSession s : sessions) {
                    s.sendMessage(new TextMessage(session.getPrincipal() + ": " + payload));
                }
            }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        session.close();
    }

}
