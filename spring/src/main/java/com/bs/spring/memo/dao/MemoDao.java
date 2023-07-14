package com.bs.spring.memo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.demo.model.dto.Memo;

public interface MemoDao {
	
	List<Memo> memoList(SqlSession session);
	
	int memoInsert(SqlSession session, Memo memo);
}
