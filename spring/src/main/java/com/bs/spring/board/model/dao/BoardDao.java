package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.board.model.dto.Board;

public interface BoardDao {

	List<Board> selectBoardAll(SqlSession session, Map<String,Object> param);
	
	int selectBoardCount(SqlSession session);
	
	Board boardView(SqlSession session, int no);
}
