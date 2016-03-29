package websocket.config.handshakeInterceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse rsp, WebSocketHandler handler, Exception arg3) {
		System.out.println("shake hands successfully!!!nice to meet you");
		;
		
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse arg1, WebSocketHandler handler,
			Map<String, Object> map) throws Exception {
		System.out.println("before shake hands");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            String user = (String) session.getAttribute("user");
//            String userName = "xq";
            if (session != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息
//                String userName = (String) session.getAttribute("WEBSOCKET_USERNAME");
                map.put("WEBSOCKET_USERNAME",user);
                
            }
        }
        return true;
	}
}