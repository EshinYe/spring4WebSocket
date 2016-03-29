package websocket.handler;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class SystemWebSocketHandler implements WebSocketHandler {
	 
 
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();;
 
 
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	System.out.println("ConnectionEstablished");
        users.add(session);
        //获取当前发送者
        String currentUser = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        TextMessage comeNotice = new TextMessage(currentUser +" join !!!");
        sendMessageToUsers(comeNotice);
    }
 
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
 //获取当前发送者
    	String currentUser = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        sendMessageToUsers((TextMessage) message,currentUser);
    }
 
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        users.remove(session);
    }
 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    	String currentUser = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        users.remove(session);
        TextMessage comeNotice = new TextMessage(currentUser +" left!!!");
        sendMessageToUsers(comeNotice);
    }
 
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
 
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message,String currentUser) {
        for (WebSocketSession user : users) {
            try {//发送者不接收
                if (user.isOpen()&&!user.getAttributes().get("WEBSOCKET_USERNAME").equals(currentUser)) {
                	String msg = currentUser+":"+message.getPayload();
                    user.sendMessage(new TextMessage(msg));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 用户进入聊天室通知
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
    	for (WebSocketSession user : users) {
    		try {
    			if (user.isOpen()) {
    				user.sendMessage(message);
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("WEBSOCKET_USERNAME").equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
 
}