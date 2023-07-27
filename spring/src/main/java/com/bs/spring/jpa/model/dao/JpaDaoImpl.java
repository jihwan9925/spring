package com.bs.spring.jpa.model.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.entity.JpaMember;

@Repository
public class JpaDaoImpl implements JpaDao {

	@Override
	public void basictest(EntityManager em) {
		// em메소드 이용하기
		//1. JpaMember클래스 영속성 등록하기
		JpaMember m = JpaMember.builder()
						.memberId("20200120")
						.memberPwd("rhrhrh22")
						.age(25)
						.height(178.2)
						.build();
		
		//영속화 처리하기
		em.persist(m); // 매개변수로 전달된 객체가 영속성 컨텍스트에 저장이 되고, 
					   // 영속성 컨텍스트에 새로 저장되면 insert sql문을 자동으로 생성
		
		JpaMember m2 = JpaMember.builder()
				.memberId("test")
				.memberPwd("test")
				.age(26)
				.height(170)
				.build();
		
		em.persist(m2);
		
		
		System.out.println(m);
		
		//저장된 객체 불러오기 : select문
		JpaMember selectM = em.find(JpaMember.class,1L);
		//select문을 가지고 오는 로직에서 변경된 값이 없으면 dto있는 값을 불러온다.
		System.out.println(selectM);

	}

}
