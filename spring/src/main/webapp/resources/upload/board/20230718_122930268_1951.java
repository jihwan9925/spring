package com.bs.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

	private BoardService service;
	
	public BoardController(BoardService service) {
		this.service=service;
	}
	
	
	@RequestMapping("/boardList.do")
	public String selectBoardAll(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="5") int numPerpage,
			Model m) {
		//페이지에 맞는 데이터를 가져와야함.
		List<Board> boards=service.selectBoardAll(Map.of("cPage",cPage,"numPerpage",numPerpage));
		int totalData=service.selectBoardCount();
		//paging
		m.addAttribute("pageBar",PageFactory.getPage(cPage,numPerpage,totalData,
				"boardList.do"));
		
		m.addAttribute("totalData",totalData);
		m.addAttribute("boards",boards);
		
		
		return "board/boardList";
	}
	
	@RequestMapping("/boardForm.do")
	public String boardForm() {
		return "board/boardForm";
	}
	
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(Board b, MultipartFile upFile, 
			HttpSession session) {
		log.info("{}",b);
		log.info("{}",upFile);
		
		//MultipartFile에서 제공하는 메소드를 이용해서 
		//파일을 저장할 수 있음 -> transferTo()메소드를 이용
		//절대경로 가져오기
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		//파일명에 대한 renamed규칙을 설정
		//직접리네임규칙을 만들어서 저장해보자.
		//yyyyMMdd_HHmmssSSS_랜덤값
		String oriName=upFile.getOriginalFilename();
		String ext=oriName.substring(oriName.lastIndexOf("."));
		Date today=new Date(System.currentTimeMillis());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
		int rdn=(int)(Math.random()*10000)+1;
		String rename=sdf.format(today)+"_"+rdn+ext;
		
		try {
			upFile.transferTo(new File(path+rename));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		Attachment file=Attachment.builder()
				.originalFilename(oriName)
				.renamedFilename(rename)
				.build();
		
		b.setFile(file);
		
		service.insertBoard(b);
		
		
		
		return "redirect:/board/boardList.do";
	}

	@RequestMapping("/boardView.do")
	public String selectBoardByNo(Model m,int no) {
		m.addAttribute("board",service.selectBoardById(no));
		return "board/boardView";
	}
	
	
}
