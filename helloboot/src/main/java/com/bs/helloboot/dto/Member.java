package com.bs.helloboot.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

	private String userId;
	private String password;
	private String userName;
	private int age;
	private String gender;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate;
	
}
