package com.bs.spring.jpa.model.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.common.Level;
import com.bs.spring.jpa.common.Role;
import com.bs.spring.jpa.entity.Address;
import com.bs.spring.jpa.entity.JpaMember;

@Repository
public class JpaDaoImpl implements JpaDao {

	@Override
	public void basictest(EntityManager em) {
		// em메소드 이용하기
		//1. JpaMember클래스 영속성 등록하기
		JpaMember m = JpaMember.builder()
						.memberId("20200121")
						.memberPwd("rhrhrh22")
						.age(new BigDecimal(27))
						.height(178.2)
						.level(Level.DIAMOND)
						.role(Role.ADMIN)
						.birthDay(new Date(java.sql.Date.valueOf(LocalDate.of(1998, 8, 3)).getTime()))
						.startDate(new Date(java.sql.Timestamp.valueOf(LocalDateTime.of(1998, 8, 3,10,30)).getTime()))
						.addr(Address.builder().statement("경기도").detailAddress("시흥시 배곧동").zipcode("123-456").build())
						.build();
		
		//영속화 처리하기
		em.persist(m); // 매개변수로 전달된 객체가 영속성 컨텍스트에 저장이 되고, 
					   // 영속성 컨텍스트에 새로 저장되면 insert sql문을 자동으로 생성
		
		JpaMember m2 = JpaMember.builder()
				.memberId("test02")
				.memberPwd("test02")
				.age(new BigDecimal(27))
				.height(170)
				.level(Level.GOLD)
				.role(Role.USER)
				.build();
		
		em.persist(m2);
		
		
		System.out.println(m);
		
		//저장된 객체 불러오기 : select문
		JpaMember selectM = em.find(JpaMember.class,1L);
		//select문을 가지고 오는 로직에서 변경된 값이 없으면 dto있는 값을 불러온다.
		System.out.println(selectM);

	}

}
