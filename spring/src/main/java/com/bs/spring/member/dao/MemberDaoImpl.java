package com.bs.spring.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.model.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public int insertMember(SqlSession session, Member member) {
		return session.insert("member.insertMember",member);
	}

	@Override
	public Member login(SqlSession session, Member member) {
		return session.selectOne("member.login",member);
	}
	
	@Override
	public int update(SqlSession session, Member member) {
		return session.update("member.update",member);
	}
}
