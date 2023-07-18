package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.dto.Board;

public interface BoardService {
		
	int insertBoard(Board b);
	
	List<Board> selectBoardAll(Map<String,Object> param);
	
	int selectBoardCount();
	
	Board selectBoardById(int no);
	
	
}
