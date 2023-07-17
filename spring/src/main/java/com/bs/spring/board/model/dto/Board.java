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
public class Board {
 
     private int boardNo;
     private String boardTitle;
     private String boardWriter;
     private String boardContent;
     private Date boardDate;
     private int boardReadCount;
     
}
