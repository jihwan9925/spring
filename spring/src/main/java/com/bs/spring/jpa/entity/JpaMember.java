package com.bs.spring.jpa.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.bs.spring.jpa.common.Level;
import com.bs.spring.jpa.common.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//일반 pojo 클래스를 Entity로 등록하기 위해서 @Entity를 사용
//사용법 : 클래스 선언부에 사용
//특징 : 
//	기본생성자는 필수
//	final클래스(enum, interface, inner)는 사용불가, 필드에는 final사용불가
@Entity//불러올 이름은 기본값으로 클래스이름이고 변경하고싶으면(name = "이름")사용

//@Table이용하기 : Table에 대한 설정을하는 어노테이션
//schema, catalog, 테이블레벨 제약조건설정(unique,pk,fk,등등), DB에 생성될 테이블명 설정(생략시 클래스명으로 테이블 생성)
//@Table()

//@SequenceGenerator : DB에서 사용할 sequence를 생성하는 어노테이션, id값을 자동부여할 때 사용
// 설정 : name(시퀀스이름), sequenceName(DB의 시퀀스이름), initValue(시작값), allocationSize(증가값)
//@TableGenerator : id값을 중복없이 발급하는 테이블 생성해서 id부여하는 용도(알아만 두기)

//@JsonIdentityInfo : entity객체를 가져올 때 양방향으로 1 대 多, 多 대 1 관계에 있으면
//					  무한루프현상이 발생하는데, 이를 차단하는 어노테이션
//@Table(name = "memberjpa")
@SequenceGenerator(name = "seq_jpamemberno", sequenceName = "seq_jpamemberno",
					initialValue = 1, allocationSize = 1)
public class JpaMember {
	@Id //entity를 식별하는 식별자가, DB에서는 Primary key제약조건이 설정된다.
	@GeneratedValue(generator = "seq_jpamemberno", strategy = GenerationType.SEQUENCE)
	//시퀀스 설정을 dto에 적용하는 방법 (strategy : 어노테이션으로 설정한 dto에 자동으로 시퀀스를 부여)
	@Column(name="member_no")
	private Long memberNo;
	@Column(name="member_id",unique=true,nullable=false,length=20)
	private String memberId;
	@Column(name="member_pwd",nullable=false,length=20)
	private String memberPwd;
	@Column(precision=10, scale=3)
	private BigDecimal age;
	@Column(columnDefinition = "number default 100.0")
	private double height;
	
	//EnumType를 이용해서 처리하기
	@Column(name="member_role")
	@Enumerated(EnumType.STRING)//문자열로 저장
	private Role role;
	@Column(name="member_level")
	@Enumerated(EnumType.ORDINAL)//문자열과 연결되어있는 숫자로 저장
	private Level level;
	
	//날짜타입에 대해 설정하기
	@Temporal(TemporalType.DATE)
	private java.util.Date birthDay;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date startDate;
	
	//lob타입 설정하기
	//자료형이 String이면 clob, byte면 blob
	@Lob
	private String info;
	@Lob
	private byte[] dataSample;
	
	//DB컬럼 대상에서 제외하기
	@Transient
	private String tempData;
	
	//임베디드 설정하기
	@Embedded
	private Address addr;
	
	
	
	
	
	
	
	
	
	
}
