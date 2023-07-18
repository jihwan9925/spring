package com.bs.spring.board.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {

	private int attachmentNo;
	private int boardNo;
	private String originalFileName;
	private String renameFileName;
	private Date upoloadDate;
	private int downloadCount;
}
