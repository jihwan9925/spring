package com.bs.helloboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

import com.bs.helloboot.dto.MyAuthority;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private DbConnectProvider provider;
	
	public SecurityConfig(DbConnectProvider provider) {
		this.provider = provider;
	}
	
	//boot에서 security 적용하기
	//1. 인증처리할 bean등록하기
	//	인증관련 설정하는 bean(SecurityFilterChain bean등록)
	//2. 인증방법에 대한 설정 클래스 등록
	//	1) inMemory, 2)DB연동방식 ->provider를 등록
	
	@Bean
	public SecurityFilterChain authenticationPath(HttpSecurity http) throws Exception{
		
		return http.csrf().disable()
				.formLogin()
					.successForwardUrl("/successLogin")
					.failureForwardUrl("/errorLogin")
					.usernameParameter("userId")
					.passwordParameter("pw")
					.loginProcessingUrl("/login.do")
					
				.and()
				.authorizeHttpRequests() // = 인터셉터 등록
					.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
					.antMatchers("/loginpage").permitAll()
					.antMatchers("/**").hasAnyAuthority(MyAuthority.ADMIN.name(),MyAuthority.USER.name())
				.and()
				.logout()
					.logoutSuccessUrl("/logout")
					.logoutUrl("/logout.do")
				.and()
				.authenticationProvider(provider)
				.build();
	}
}
