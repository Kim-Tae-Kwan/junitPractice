package com.ktk.junitPractice.service;

import org.springframework.stereotype.Service;

import com.ktk.junitPractice.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	
	private final BookRepository bookRepository;
	
	
	// 1. 책 등록
	// 2. 책 목록보기
	// 3. 책 한권 보기
	// 4. 책 삭제
	// 5. 책 수정
}
