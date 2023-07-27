package com.bs.helloboot.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.bs.helloboot.common.interceptor.LoggerInterceptor;
import com.bs.helloboot.websocket.ChattingServer;

@Configuration
@EnableWebSocket
//@EnableWebMvc
public class MyWebMvcConfiguration implements WebMvcConfigurer,WebSocketConfigurer{

	private ChattingServer chatting;
	
	public MyWebMvcConfiguration(ChattingServer chatting) {
		this.chatting = chatting;
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatting, "/chatting");
	}
	
	//view에 대한 설정
	//기본 화면전환에 대한 설정을 하는 메소드를 재정의 할 수 있다.
	// *(페이지를 연결해주는 controller메소드의 역할을 대신 할 수 있다.[데이터전달이 없고 페이지연결만 있을 떄 대체가능])
	// *하나의 메소드에 복수의 페이지연결을 가능하게 해준다.
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/test").setViewName("test");
		registry.addViewController("/chattingpage").setViewName("chattingpage");
	}
	
	//interceptor설정
	//*경로중간에 로그를 띄우거나 로직을 구현하기 위해 사용되는 로직
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/member/*");
	}

	//cors에 대한 허용설정(cross-origin resource sharing) error
	//자바스크립트가 발생시키는 에러
	//다른 Origin(서버)에서 오는 요청이라면 믿을만한지 알수 없기 떄문에 에러를 발생 시키는데 이를 cors라고 한다.
	//api같은 경우가 예시로 들 수 있는데 해결하기 위해 아래와 같은 로직을 이용하면 된다.
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:3000");
	}
	
	//HandlerExceptionResolver를 이용해서 spring에서 발생하는 에러 처리하기 (web mvc모듈에서 제공)
	@Bean
	public HandlerExceptionResolver handleExceptionResolver() {
		Properties exceptionProp = new Properties();
		exceptionProp.setProperty(IllegalAccessException.class.getName(),
				"error/accessException");
		
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		resolver.setExceptionMappings(exceptionProp);
		resolver.setDefaultErrorView("error/error");
		return resolver;
	}
	
}
