package com.bs.spring.beantest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.Data;

//pojo클래스를 생성하고, 선언부에서 bean으로 등록할 수 있다.
// @Component, @Controller, @Service, @Repository
// 어노테이션을 이용해서 spring bean으로 등록
// @Component : 기본 springbean으로 등록할 때 사용 (클래스를 bean으로 등록)
// @Controller, @Service, @Repository(dao) : mvc패턴에 의해 역할 지정된 클래스를 등록할 때 사용

@Data
@Component
public class FunctionalTest {
	private String name="test";
	
	//@Autowired
	private Animal a;
	
	//생성자를 이용한 DI(의존성 주입)
//	public FunctionalTest(@Qualifier("dog") Animal a) {
//		this.a=a;
//	}
	
	//setter를 이용한 DI(의존성 주입)
	@Autowired
	public void setA(@Qualifier("dog") Animal a) {
		this.a=a;
	}
	public Animal getA() {
		return this.a;
	}
}
