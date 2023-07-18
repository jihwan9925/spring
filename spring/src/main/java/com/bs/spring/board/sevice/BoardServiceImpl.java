package com.bs.spring.board.sevice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<Board> selectBoardAll(Map<String,Object> param) {
		return dao.selectBoardAll(session,param);
	}
	
	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount(session);
	}
	
	@Override
	public Board boardView(int no) {
		return dao.boardView(session,no);
	}
	
	//@Transactional
	@Override
	public int insertBoard(Board b) {
		
		//2개의 insert문을 실행
		log.info("실행전 : {}",b.getBoardNo());
		int result=dao.insertBoard(session, b);
		log.info("실행후 : {}",b.getBoardNo());
		if(result>0) {
			if(b.getFile().size()>0) {
				for(Attachment a:b.getFile()) {
					a.setBoardNo(b.getBoardNo());
					result+=dao.insertAttachment(session,a);
					//한개씩 트렌젝션 처리 (하려면 위에는 등호만 사용해야함)
					//if(result!=1)throw new RuntimeException("업로드의 문제가 발생했습니다.");
				}
			}
		}
		//한번에 트렌젝션 처리
		//rollback처리를 원한다면 RuntimeException을 발생시키면 된다.
		if(result!=b.getFile().size()+1)throw new RuntimeException("업로드의 문제가 발생했습니다.");
		//if(result!=0)throw new RuntimeException("업로드의 문제가 발생했습니다.");
		return result;
	}

}
