package com.bs.spring.member.model.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	@NotEmpty
	@Size(message="크기가 4보다 커야합니다.", min=4)
	private String userId;
	@Pattern(message ="대문자,소문자,특수기호포함 8글자이상",
			regexp = "(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[~!@#$%^&*()])[a-zA-Z~!@#$%^&*()]{8,}")
	private String password;
	
	private String userName;
	
	private String gender;
	@Min(value=14, message="14살이상 입력해주세요.") @Max(150)
	private int age;
	@Email
	private String email;
	@NotEmpty
	private String phone;
	
	private String address;
	
	private String[] hobby;
	
	private Date enrollDate;
	
}
