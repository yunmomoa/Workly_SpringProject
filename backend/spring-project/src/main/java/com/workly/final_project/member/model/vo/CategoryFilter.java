package com.workly.final_project.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryFilter {
	private String deptNo;
	private String positionNo;
	private String status;
	private String name;
}
