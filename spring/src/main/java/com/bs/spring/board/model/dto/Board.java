package com.bs.spring.board.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.bs.spring.member.model.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
 
     private int boardNo;
     private String boardTitle;
     private Member boardWriter;
     private String boardContent;
     private Date boardDate;
     private int boardReadCount;
     private List<Attachment> file = new ArrayList();
     
}
