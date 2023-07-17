package com.bs.spring.board.sevice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.dto.Board;

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

}
