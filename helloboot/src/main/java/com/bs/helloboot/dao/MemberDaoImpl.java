package com.bs.helloboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.helloboot.common.mapper.MemberMapper;
import com.bs.helloboot.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	private MemberMapper mapper;
	
	public MemberDaoImpl(MemberMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public List<Member> selectMemberAll(SqlSession session) {
		// TODO Auto-generated method stub
		//return session.selectList("member.selectMemberAll");
		
		//mapper를 인터페이스의 어노테이션방식으로 구현했을 때
		return mapper.selectMemberAll();
	}

	@Override
	public int insertMember(SqlSession session, Member m) {
		// TODO Auto-generated method stub
		return session.insert("member.insertMember",m);
	}

	@Override
	public Member selectMemberById(String userId) {

		return mapper.selectMemberById(userId);
	}

	@Override
	public List<Member> selectMemberByName(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapper.selectMemberByName(param);
	}


}
