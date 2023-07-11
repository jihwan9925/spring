package com.bs.spring.beantest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
	private Long deptCode;
	private String deptTitle;
	private String deptLocation;
}
