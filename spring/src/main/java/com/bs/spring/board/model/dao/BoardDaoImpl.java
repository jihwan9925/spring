package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.dto.Board;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Override
	public List<Board> selectBoardAll(SqlSession session, Map<String,Object> param) {
		int cPage = (int)param.get("cPage");
		int numPerpage = (int)param.get("numPerpage");
		RowBounds rb = new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("board.selectBoardAll",null,rb);
	}
	
	@Override
	public int selectBoardCount(SqlSession session) {
		return session.selectOne("board.selectBoardCount");
	}
	
	@Override
	public Board boardView(SqlSession session, int no) {
		return session.selectOne("board.boardView",no);
	}

}
