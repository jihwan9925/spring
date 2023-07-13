package com.bs.spring.member.service;

import com.bs.spring.member.model.dto.Member;

public interface MemberService {
	public int insertMember(Member member);
	
	public Member login(Member member);
}
