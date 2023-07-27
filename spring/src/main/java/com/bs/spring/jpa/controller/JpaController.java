package com.bs.spring.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.jpa.model.service.JpaService;

@Controller
@RequestMapping("/jpa")
public class JpaController {

	private JpaService service;
	
	public JpaController(JpaService service) {
		this.service = service;
	}
	
	@GetMapping("basicTest.do")
	public String basicTest() {
		service.basictest();
		return "redirect:/";
	}
}
