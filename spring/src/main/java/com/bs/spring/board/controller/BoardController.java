package com.bs.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.sevice.BoardService;
import com.bs.spring.common.PageFactory;
import com.bs.spring.member.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		m.addAttribute("board", board);
		
		return "/board/boardView";
	}
	
	@RequestMapping("/boardForm.do")
	public String boardForm() {
		return "/board/boardForm";
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(Board b, String userId, MultipartFile[] upFile, HttpSession session, Model m) {
		//dto에 boardWriter가 member타입으로 바꿨기 때문에 form으로 보내는 name을 userId로 맞추고,
		//그 값을 Member로 받아옴으로서 boardWriter와 자료형을 맞춘다.
		b.setBoardWriter(Member.builder().userId(userId).build());
//		b.getBoardWriter().setUserId(userId); // Member자료형을 생성하지 않았기 때문에 오류가 뜬다.
		// b.getBoardWriter()로 접근하면 안되는 이유 : Member를 생성하기 전이기 때문에 불러와도 null이 뜬다.
		// 그러므로 dto에 생성로직을 넣거나 builder를 이용해서 생성까지 한번에 해결하는 방법이 있다.(
		//	EX = b.setBoardWriter(Member.builder().userId(userId).build());
		//)
		log.info("{}",b);
		log.info("{}",upFile);
		
		
		//MultipartFile에서 제공하는 메소드를이용해서
		//파일을 저장할 수 있다 (tramsferTo()메소드 사용)
		//파일명에 대한 rename규칙을 설정
		//직접rename규칙을 만들어 저장하기
		//규칙 : yyyyMMdd_HHmmssSSS_랜덤값
		//원본 파일에서 확장자 가져오기 위해 불러옴
		
		//파일을 저장하기위한 절대경로 가져오기
		String path = session.getServletContext().getRealPath("/resources/upload/board/");
		
		if(upFile!=null) {
			for(MultipartFile mf:upFile) {
				if(!mf.isEmpty()) {
					
					String oriName = mf.getOriginalFilename();
					String ext = oriName.substring(oriName.lastIndexOf("."));
					Date today = new Date(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
					int rdn = (int)(Math.random()*10000)+1;
					String rename = sdf.format(today)+"_"+rdn+ext;
					
					try {
						mf.transferTo(new File(path+rename));				
					}catch(IOException e) {
						e.printStackTrace();
					}
					
					Attachment file = Attachment.builder()
							.originalFileName(oriName)
							.renameFileName(rename)
							.build();
					
					b.getFile().add(file);
				}
			}
		}
		try {
			service.insertBoard(b);			
		}catch(RuntimeException e) {
			for(Attachment a : b.getFile()) {
				File delFile=new File(path+a.getRenameFileName());
				delFile.delete();
			}
			m.addAttribute("msg", "글쓰기 등록실패");
			m.addAttribute("loc", "/board/boardForm.do");
			return "common/msg";
		}
		
		return "redirect:/board/boardList.do";
	}
	
	@RequestMapping("/filedownload")
	public void fileDown(String oriName, String reName, OutputStream out,
			@RequestHeader(value="user-agent") String header, HttpSession session, HttpServletResponse res) {
		
		String path = session.getServletContext().getRealPath("/resources/upload/board/");
		File downloadFile = new File(path+reName);
		try(FileInputStream fis = new FileInputStream(downloadFile);
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedOutputStream bos = new BufferedOutputStream(out)){
			
			boolean isMS = header.contains("Trident")||header.contains("MSIE");
			String encodeRename = "";
			if(isMS) {
				encodeRename = URLEncoder.encode(oriName,"UTF-8");
				encodeRename = encodeRename.replaceAll("\\+", "%20");
			}else {
				encodeRename = new String(oriName.getBytes("UTF-8"),"ISO-8859-1");
			}
			res.setContentType("application/octet-stream;charset=utf-8");
			res.setHeader("Content-Disposition","attachment;filename=\""+encodeRename+"\"");
			
			int read=-1;
			while((read=bis.read())!=-1) {
				bos.write(read);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
