package com.ktk.junitPractice.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.ktk.junitPractice.domain.entiry.Book;

import jakarta.transaction.Transactional;

@DataJpaTest
class BookRepositoryTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@BeforeEach
	public void init() {
		String title = "junit-init";
		String author = "initializer";
		Book book = Book.builder()
						.title(title)
						.author(author)
						.build();
		bookRepository.save(book);
		System.out.println("============ init ==================");
	}
	
	// 1. 책 등록
	@Test
	public void addBook() {
		// given
		String title = "junit5";
		String author = "ktk";
		Book book = Book.builder()
						.title(title)
						.author(author)
						.build();
		// when
		Book bookPS = bookRepository.save(book);
		
		// then
		assertEquals(title, bookPS.getTitle());
		assertEquals(author, bookPS.getAuthor());
	}
	// 2. 책 전체 조회
	@Test
	public void getBooks() {
		// given
		String title = "title";
		String author = "author";
		for(int i = 0; i < 5; i++) {
			Book book = Book.builder()
					.title(title + i)
					.author(author + i)
					.build();
			bookRepository.save(book);
		}
		
		// when
		List<Book> books = bookRepository.findAll();
		
		// then
		assertEquals(6, books.size());
	}
	// 3. 책 한권 조회 - by id
	@Test
	@Sql("classpath:sql/initDB.sql")
	public void getBook() {
		// given
		Long id = 1L;
		String title = "junit-init";
		String author = "initializer";
		
		// when
		Book book = bookRepository.findById(id).get();
		// then
		assertEquals(id, book.getId());
		assertEquals(title, book.getTitle());
		assertEquals(author, book.getAuthor());
	}
	// 4. 책 수정
	@Test
	@Sql("classpath:sql/initDB.sql")
	public void updateBook() {
		// given
		Long id = 1L;
		String title = "update";
		String author = "ktk";
		
		// 1. Book 생성 -> save
//		Book book = Book.builder()
//						.id(id)
//						.title(title)
//						.author(author)
//						.build();
		
		
		// 2. Book 조회 -> title, author update -> save
		Book book = bookRepository.findById(id).get();
		book.setTitle(title);
		book.setAuthor(author);
		
		// when
		bookRepository.save(book);
		Book bookPS = bookRepository.findById(id).get(); 
		
		// then
		assertEquals(id, bookPS.getId());
		assertEquals(title, bookPS.getTitle());
		assertEquals(author, bookPS.getAuthor());
	}
	
	// 5. 책 삭제
	@Test
	public void deleteBook() {
		//given
		String title = "junit5";
		String author = "ktk";
		Book book = Book.builder()
						.title(title)
						.author(author)
						.build();
		bookRepository.save(book);
		
		//when
		bookRepository.delete(book);
		List<Book> books = bookRepository.findAll();
		//then
		assertEquals(1, books.size());
	}
}
