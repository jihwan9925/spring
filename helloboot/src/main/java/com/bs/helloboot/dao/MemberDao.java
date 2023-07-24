package com.bs.helloboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.helloboot.dto.Member;

public interface MemberDao {

	List<Member> selectMemberAll(SqlSession session);
	
	int insertMember(SqlSession session, Member m);
	
	Member selectMemberById(String userId);
	
	List<Member> selectMemberByName(Map<String, Object> param);
}
