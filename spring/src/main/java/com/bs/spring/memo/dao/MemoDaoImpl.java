package com.bs.spring.memo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.demo.model.dto.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {

	@Override
	public List<Memo> memoList(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectList("memo.memoList");
	}
	
	@Override
	public int memoInsert(SqlSession session, Memo memo) {
		// TODO Auto-generated method stub
		return session.insert("memo.memoInsert",memo);
	}

}
