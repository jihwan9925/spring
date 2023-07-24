package com.bs.helloboot.service;

import java.util.List;
import java.util.Map;

import com.bs.helloboot.dto.Member;

public interface MemberService {

	List<Member> selectMemberAll();
	
	int insertMember(Member m);
	
	Member selectMemberById(String userId);
	
	List<Member> selectMemberByName(Map<String,Object> param);
}
