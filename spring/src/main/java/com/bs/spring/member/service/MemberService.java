package com.bs.spring.member.service;

import java.util.List;

import com.bs.spring.member.model.dto.Member;

public interface MemberService {
	public int insertMember(Member member);
	
	public Member login(Member member);
	
	public int update(Member member);
	
	public List<Member> selectMemberAll();
}
