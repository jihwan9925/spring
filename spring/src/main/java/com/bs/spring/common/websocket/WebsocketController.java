package com.bs.spring.common.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebsocketController {

	@RequestMapping("/chattingPage")
	public String chattringPage() {
		return "chatting/chat";
	}
}
