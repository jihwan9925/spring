package com.bs.spring.memo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.MainController;
import com.bs.spring.demo.model.dto.Memo;
import com.bs.spring.memo.service.MemoService;

@Controller
public class MemoController {
	
	//logger 생성하기
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
//	@Autowired
	private MemoService service;
	
	public MemoController(MemoService service) { // @Autowired대신에 생성자의 매개변수에 등록해서 사용가능
		this.service=service;
	}
	
	@RequestMapping("/memo/memoList.do")
	public String memoList(Model m) {
		List<Memo> result = service.memoList();
		m.addAttribute("memo",result);
		return "memo/memo";
	}
	
	@PostMapping("/memo/memoInsert.do")
	public String memoInsert(Memo memo, Model m) {
		int result = service.memoInsert(memo);
		if(result>0) {
			//성공
			m.addAttribute("msg", "메모생성 성공");
			m.addAttribute("loc", "/");
		}else {
			//실패
			m.addAttribute("msg", "메모생성 실패");
			m.addAttribute("loc", "/memo/memo");
		}
		return "common/msg";
	}
	
}
