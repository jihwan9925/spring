package com.bs.spring.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.sevice.BoardService;
import com.bs.spring.common.PageFactory;


@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@RequestMapping(value="/boardList.do")
	public String selectBoardAll(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="5") int numPerpage,Model m) {
		
		List<Board> result = service.selectBoardAll(Map.of("cPage",cPage,"numPerpage",numPerpage));
		m.addAttribute("board",result);

		int totalData = service.selectBoardCount();
		m.addAttribute("totalContents", totalData);
		
		m.addAttribute("pageBar",PageFactory.getPage(cPage,numPerpage,totalData,"/spring/board/boardList.do"));
		
		return "board/boardList";
	}
	
	@RequestMapping(value="/boardView.do")
	public String boardView(HttpServletRequest request, HttpServletResponse response, int no,  Model m) {
		//쿠키 조회 view에서 할거
		Cookie[] cookies=request.getCookies();
		String boardRead="";
		boolean isRead=false;
		if(cookies!=null) {	
			for(Cookie c : cookies) {
				if(c.getName().equals("noticeRead")) { //쿠키에 key값이 boardRead와 같으면 true
					boardRead=c.getValue(); //해당 key의 value를 저장
					if(boardRead.contains("|"+no+"|")) { //지정한 양식(정확한 조회수 집계를위해 사용)이 있으면
						isRead=true;
					}
					break;
				}
			}
		}
		//쿠키 생성 , 쿠키에 지정한 양식이 없으면 true
		if(!isRead) {
			Cookie c=new Cookie("boardRead",boardRead+"|"+no+"|");
			c.setMaxAge(60*60*24);
			response.addCookie(c);			
		}
		
		//상세 내용 불러오기
		Board board = service.boardView(no);
		m.addAttribute("bView", board);
		
		return "/board/boardView";
	}
}
