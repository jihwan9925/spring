package com.bs.helloboot.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.helloboot.dao.MemberDao;
import com.bs.helloboot.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {

	
	private MemberDao dao;
	private SqlSession session;
	
	public MemberServiceImpl(MemberDao dao, SqlSession session) {
		this.dao = dao;
		this.session = session;
	}
	
	
	@Override
	public List<Member> selectMemberAll() {
		// TODO Auto-generated method stub
		return dao.selectMemberAll(session);
	}

	@Override
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		return dao.insertMember(session,m);
	}


	@Override
	public Member selectMemberById(String userId) {
		// TODO Auto-generated method stub
		return dao.selectMemberById(userId);
	}


	@Override
	public List<Member> selectMemberByName(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dao.selectMemberByName(param);
	}
	

}
