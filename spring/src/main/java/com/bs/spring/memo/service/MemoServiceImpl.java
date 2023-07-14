package com.bs.spring.memo.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.demo.model.dto.Memo;
import com.bs.spring.memo.dao.MemoDao;

@Service
public class MemoServiceImpl implements MemoService {
	
	private MemoDao dao;
	
	private SqlSession session;
	
	public MemoServiceImpl(MemoDao dao, SqlSession session) {
		this.dao = dao;
		this.session = session;
	}
	
	@Override
	public List<Memo> memoList() {
		// TODO Auto-generated method stub
		return dao.memoList(session);
	}
	
	@Override
	public int memoInsert(Memo memo) {
		// TODO Auto-generated method stub
		return dao.memoInsert(session, memo);
	}

}
