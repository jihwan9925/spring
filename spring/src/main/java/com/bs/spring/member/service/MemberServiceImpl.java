package com.bs.spring.member.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao dao;
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		return dao.insertMember(session,member);
	}
	
	@Override
	public Member login(Member member) {
		return dao.login(session,member);
	}
	
	@Override
	public int update(Member member) {
		return dao.update(session,member);
	}

}
