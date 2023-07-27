package com.bs.spring.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private Long memberNo;
	
	private String memberId;
	private String memberPwd;
	private Integer age;
	private double height;
}
