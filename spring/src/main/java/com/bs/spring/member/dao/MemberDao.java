package com.bs.spring.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.member.model.dto.Member;

public interface MemberDao {
	public int insertMember(SqlSession session, Member member);
	
	public Member login(SqlSession session, Member member);
	
	public int update(SqlSession session, Member member);
	
	public List<Member> selectMemberAll(SqlSession session);
}
