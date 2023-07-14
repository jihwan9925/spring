package com.bs.spring.memo.service;

import java.util.List;

import com.bs.spring.demo.model.dto.Memo;

public interface MemoService {
	
	List<Memo> memoList();
	
	int memoInsert(Memo memo);
}
