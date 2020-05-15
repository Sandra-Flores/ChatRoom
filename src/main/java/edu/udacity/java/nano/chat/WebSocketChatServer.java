package edu.udacity.java.nano.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat/{username}")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String msg) throws IOException {

        for(Map.Entry<String, Session> entry: onlineSessions.entrySet()) {
            if (entry.getValue().isOpen()){
                entry.getValue().getBasicRemote().sendText(msg);
            }
        }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String userName) throws IOException {
        onlineSessions.put(userName, session);

        ObjectMapper objectMapper = new ObjectMapper();
        Message message = new  Message (userName, "JOIN", onlineSessions.size());
        message.setMsg("");

        sendMessageToAll(objectMapper.writeValueAsString(message));

    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Message message = objectMapper.readValue(jsonStr, Message.class);

        message.setType("CHAT");
        message.setOnlineCount(onlineSessions.size());

        sendMessageToAll(objectMapper.writeValueAsString(message));

    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session, @PathParam("username") String userName) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Message message = new Message();
        message.setType("LEAVE");
        message.setUsername(userName);
        message.setMsg("");

        onlineSessions.remove(userName);
        message.setOnlineCount(onlineSessions.size());

        sendMessageToAll(objectMapper.writeValueAsString(message));

    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
