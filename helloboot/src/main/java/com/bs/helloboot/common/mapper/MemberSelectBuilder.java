package com.bs.helloboot.common.mapper;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class MemberSelectBuilder {

	public static String selectMemberByName(Map<String,Object> param) {
		return new SQL() {{
				SELECT("*");
				FROM("MEMBER");
				if(param.get("userName")!=null && !param.get("userName").equals("")) {
					WHERE("USERNAME LIKE '%'||#{userName}||'%'");
				}}
		}.toString();
	}
}
