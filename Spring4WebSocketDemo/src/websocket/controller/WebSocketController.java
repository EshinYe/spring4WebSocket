package websocket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {
	
	@RequestMapping("toClient")
	public String toClient(HttpServletRequest request,String user){
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return "client" ;
	}
}
