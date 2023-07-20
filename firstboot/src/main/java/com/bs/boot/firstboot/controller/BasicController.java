package com.bs.boot.firstboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BasicController {

	@RequestMapping("/")
	public String index() {
		return "하이하이하이하이";
	}
	
	@RequestMapping("/test")
	public List<String> test() {
		log.info("우와우와우와");
		return List.of("1","2","3","4","5");
	}
}
