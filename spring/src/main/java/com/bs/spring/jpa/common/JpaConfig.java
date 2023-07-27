package com.bs.spring.jpa.common;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {

	//jpa로 DB에 접속하기 위해서
	//entityManager객체가 필요함
	//entityManager객체는 entityManagerFactory클래스의
	//createEntityManager()메소드를 이용해서 가져올 수 있다.
	//Persistence클래스의 static메소드인 createEntityManagerRactory()메소드를 이용한다.
	
	@Bean
	public static EntityManagerFactory entityManagerFactory() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bstest");
		return factory;
	}
	
	@Bean
	public EntityManager entityManager() {
		return entityManagerFactory().createEntityManager();
	}
}
