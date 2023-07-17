package com.bs.spring.board.sevice;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.dto.Board;

public interface BoardService{

	List<Board> selectBoardAll(Map<String,Object> param);
	
	int selectBoardCount();
	
	Board boardView(int no);
}
