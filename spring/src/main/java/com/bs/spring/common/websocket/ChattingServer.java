package com.bs.spring.common.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChattingServer extends TextWebSocketHandler{
	
	//key userId, session은 전달된 값을 저장
	private Map<String,WebSocketSession> clients = new HashMap();
	
	@Autowired
	private ObjectMapper mapper;
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("채팅서버 입장");
		log.info(session.getId()+" "+session.getRemoteAddress());
		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		log.info("{}",message);
		log.info(message.getPayload());//클라이언트가 보낸 데이터
		ChattingMessage msg = mapper.readValue(message.getPayload(), ChattingMessage.class);
		
		session.getAttributes().put("msg", msg);
		
		System.out.println(msg);
		switch(msg.getType()) {
			case "open" : addClient(session,msg.getSender());break;
			case "msg" :sendMessage(msg);break;
		}
	}
	private void addClient(WebSocketSession session,String sender) {
		clients.put(sender,session);
		log.info("현재접속자 : "+clients.size());
		//List<String> attence = new ArrayList(clients.keySet());
		String attence = clients.keySet().stream().map(e->e+",").collect(Collectors.joining(","));
		systemMessage(attence);
	}
	
	private void systemMessage(String msg) {
		try {
			Set<Map.Entry<String,WebSocketSession>> clients = this.clients.entrySet();
			for(Map.Entry<String,WebSocketSession> client:clients) {
				client.getValue().sendMessage(
						new TextMessage(mapper.writeValueAsString(
								ChattingMessage.builder().type("system").msg(msg).build()
								))
						);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(ChattingMessage msg) {
		Set<Map.Entry<String,WebSocketSession>> clients = this.clients.entrySet();
		try {
			if(msg.getReciever().isBlank()) {
				//전체전송
				for(Map.Entry<String,WebSocketSession> client:clients) {
//					String userId=client.getKey();
					client.getValue().sendMessage(
							new TextMessage(mapper.writeValueAsString(msg))
							);
				}
			}else{
				for(Map.Entry<String,WebSocketSession> client:clients) {
					String userId=client.getKey();
					if(userId.equals(msg.getReciever())||userId.equals(msg.getSender())) {
						client.getValue().sendMessage(
								new TextMessage(mapper.writeValueAsString(msg))
								);
					}
				}
			}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
	}

	
}
