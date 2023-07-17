package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component //bean에 등록하는 기본적인 어노테이션(controller같은 역할이 아니기때문에 큰범위의 기준으로 설정함)
@Aspect
public class AnnoLoggerAspect {

	//pointcut 설정
//	@Pointcut("execution(* com.bs.spring.member..*(..))") or
	@Pointcut("within(com.bs.spring.member..*)")
	public void loggerTest() {}
	
	//advisor 설정
	// = loggerTest()에서 설정한 기준의 @Before조건에 해당하면 해당 메소드를 실행한다.
	@Before("loggerTest()")
	public void loggerBefore(JoinPoint jp) {
		log.debug("====== annotation aop ======");
		Signature sig = jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("============================");
	}
	
	@Pointcut("execution(* com.bs.spring.memo..*(..))")
	public void memoLogger() {}
	
	@After("memoLogger()")
	public void afterLogger(JoinPoint jp) {
		log.debug("====== annotation aop after ======");
		Signature sig = jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("==================================");
	}
	
	//@Around 메소드 실행 전, 후에 특정로직을 실행할 때 사용
	@Around("execution(* com.bs.spring..*DaoImpl.*(..))") //한번만사용하려면 pointcut을 선언하여 사용할 수도 있다.
	public Object daoLogger(ProceedingJoinPoint join) throws Throwable{
		//전, 후 로직을 한번에 실행할 수 있다.
		//전, 후를 구분하는 구문 : ProceedingJoinPoint클래스가 제공하는 proceed()메소드를 이용
		//proceed()메소드가 호출한 다음 라인은 후처리, 그 전라인은 전처리
		//proceed()메소드는 Object를 반환
		//메소드 실행시간 체크하기
		StopWatch stop = new StopWatch();
		stop.start();
		log.debug("====== around logger dao before ======");
		log.debug("====== 전처리 내용 구현 ============");
		log.debug("===============================");
		Signature sig = join.getSignature();
		String classMethod = sig.getDeclaringType().getName()+sig.getClass();
		Object obj = join.proceed();
		stop.stop();
		log.debug("====== around logger dao after ======");
		log.debug("====== 후처리 내용 구현 "+stop.getTotalTimeMillis()+"ms ============");
		log.debug("===============================");
		return obj;
	}
	
	//에러처리 ==========
	@AfterThrowing(pointcut = "loggerTest()", throwing = "e")
	public void afterThrowinglogger(JoinPoint jo, Throwable e) {
		log.debug("에러발생");
		Signature sig = jo.getSignature();
		log.debug("{}",sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("{}",e.getMessage());
		StackTraceElement[] stacktrace = e.getStackTrace();
		for(StackTraceElement element : stacktrace) {
			log.debug("{}",element);
		}
	}
	
}
