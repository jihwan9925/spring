package com.bs.spring.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

@RestController
@RequestMapping("/member")
public class RestMemberController {

	@Autowired
	private MemberService service;
	
	@GetMapping
	//public List<Member> selectMemberAll(){
	public ResponseEntity<List<Member>> selectMemberAll(){
		List<Member> members = service.selectMemberAll();
		//응답할 때 값 외에도 상태를 함께보낼 수 있다.
		ResponseEntity<List<Member>> response = 
				//new ResponseEntity<>(members,HttpStatus.BAD_REQUEST);
				ResponseEntity.ok(members);
		return response;
	}
	@GetMapping("/{id}")
	//@PathVariable("id")   = 경로에 있는 변수(id)를 불러와 저장
	public Member selectMemberById(@PathVariable("id") String id){
		Member m = Member.builder().userId(id).build();
		return service.login(m);
	}
	
	@PostMapping
	public int insertMember(@RequestBody Member m){
		return service.insertMember(m);
	}
	
	@PutMapping
	public int updateMember(@RequestBody Member m){
		return service.update(m);
	}
	
	/*
	 * @DeleteMapping 
	 * public int deleteMember(@RequestBody Member m){ 
	 * return service.deleteMember(m); }
	 * 
	 * @DeleteMapping
	 * public int deleteMember(@RequestBody Member m) {
	 * return service.deleteMember(m);}
	 */
	
	//ex : 특정 게시글에 댓글들 가져오기
	//예시주소 : /board/{no}/comment/{commentno}
	
	
}
