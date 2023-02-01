package com.ktk.junitPractice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktk.junitPractice.domain.dto.BookResponseDto;
import com.ktk.junitPractice.domain.dto.BookSaveReqDto;
import com.ktk.junitPractice.domain.entiry.Book;
import com.ktk.junitPractice.repository.BookRepository;
import com.ktk.junitPractice.util.MailSender;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	
	private final BookRepository bookRepository;
	private final MailSender mailSender;
	
	// 1. 책 등록
	@Transactional(rollbackFor = RuntimeException.class)
	public BookResponseDto saveBook(BookSaveReqDto dto) {
		Book bookPS = bookRepository.save(dto.toEntity());
		if(bookPS != null) {
			
			if(!mailSender.send()) {
				throw new RuntimeException("메일 전송 실패...");
			}
		}
		
		return bookPS.toDto();
	}
	
	// 2. 책 목록보기
	public List<BookResponseDto> findAll(){
		 return bookRepository.findAll().stream().map( (book) -> { return book.toDto();} ).collect(Collectors.toList());
	}
	
	// 3. 책 한권 보기
	public BookResponseDto findOne(Long id) {
		return bookRepository.findById(id).get().toDto();
	}
	
	// 4. 책 삭제
	@Transactional(rollbackFor = RuntimeException.class)
	public void delete(Long id) {
		bookRepository.deleteById(id);
	}
	
	// 5. 책 수정
	@Transactional(rollbackFor = RuntimeException.class)
	public void update(Long id, BookSaveReqDto dto) {
		Optional<Book> bookOP = bookRepository.findById(id);
		if(bookOP.isPresent()) {
			Book bookPS = bookOP.get();
			bookPS.setTitle(dto.getTitle());
			bookPS.setAuthor(dto.getAuthor());
			
			bookRepository.save(bookPS);
		}else {
			throw new RuntimeException("Not Found book entity by id - " + id);
		}
	}	
	
}
 