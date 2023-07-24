package com.bs.spring.security.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bs.spring.member.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;

public class SecurityLoginService implements UserDetailsService{

	private MemberDao dao;
	
	private SqlSession session;
	
	@Autowired
	public SecurityLoginService(MemberDao dao, SqlSession session) {
		this.dao = dao;
		this.session = session;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member loginMember = dao.login(session,Member.builder().userId(username).build());
		return loginMember;
	}
	
	
}
