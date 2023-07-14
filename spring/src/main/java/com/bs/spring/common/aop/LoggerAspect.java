package com.bs.spring.common.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerAspect {
	
//	어떤 메소드가 실행되기 전에 공통적으로 실행되는 메소드 설정
	public void loggerBefore(JoinPoint jp) {
		log.debug("----- aop loggerBefore -----");
		Signature sig = jp.getSignature();
		
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		Object[] arg = jp.getArgs(); //메소드가 실행될 때 전달되는 매개변수의 인수값을 가져옴
		if(arg != null) {
			for(Object o : arg) {
				log.debug("{}",o);
			}
		}
		log.debug("----------------------------");
	}
	
	public void loggerAfter(JoinPoint jp) {
		log.debug("----- aop loggerAfter -----");
		Signature sig = jp.getSignature();
		
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		// before일 땐 controller->service->dao 순으로 출력되는데 after에선 반대가되는 이유 : 
		// after일 땐 종료되는 시점을 출력하기 때문에 먼저 dao가 출력된다.
		
		log.debug("----------------------------");
	}
}
