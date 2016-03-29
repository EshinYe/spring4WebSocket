package websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import websocket.config.handshakeInterceptor.WebSocketHandshakeInterceptor;
import websocket.handler.SystemWebSocketHandler;


@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		 registry.addHandler(systemWebSocketHandler(),"/webSocketServer")
		         .addInterceptors(new WebSocketHandshakeInterceptor());
		 registry.addHandler(systemWebSocketHandler(),"/sockjs/echo")
		         .addInterceptors(new WebSocketHandshakeInterceptor()).withSockJS();
//		         .setClientLibraryUrl("http://localhost:8080/Spring4WebSocketDemo/js/sockjs-1.0.3.js");
	}
	
	@Bean
    public WebSocketHandler systemWebSocketHandler(){
		
        return new SystemWebSocketHandler();
    }
	
	@Bean
	public ServletServerContainerFactoryBean createWebSocketContainer() {
		ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
		container.setMaxTextMessageBufferSize(8192);
		container.setMaxBinaryMessageBufferSize(8192);
		container.setAsyncSendTimeout(1000*5);
		return container;
	}
	

}
