package com.ktk.junitPractice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktk.junitPractice.domain.dto.BookResponseDto;
import com.ktk.junitPractice.domain.dto.BookSaveReqDto;
import com.ktk.junitPractice.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
	
	private final BookService bookService;
	
	@PostMapping
	public ResponseEntity<?> saveBook(@RequestBody @Valid BookSaveReqDto dto, BindingResult bindingResult){
		System.out.println(bindingResult.hasErrors());
		BookResponseDto response = bookService.saveBook(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllBook(){
		List<BookResponseDto> dtos = bookService.findAll();
		return ResponseEntity.status(HttpStatus.CREATED).body(dtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAllBook(@PathVariable Long id){
		BookResponseDto dto = bookService.findOne(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Long id, BookSaveReqDto dto){
		bookService.update(id, dto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Long id){
		bookService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
