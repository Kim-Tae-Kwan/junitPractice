package com.ktk.junitPractice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ktk.junitPractice.domain.dto.BookResponseDto;
import com.ktk.junitPractice.domain.dto.BookSaveReqDto;
import com.ktk.junitPractice.domain.entiry.Book;
import com.ktk.junitPractice.repository.BookRepository;
import com.ktk.junitPractice.util.MailSender;
import com.ktk.junitPractice.util.MailSenderAdapter;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
	
	@InjectMocks
	private BookService bookService;
	
	@Mock
	private MailSender mailSender;
	@Mock
	private BookRepository bookRepository;
	
	@Test
	public void saveBook() {
		// given
		String title = "junit";
		String author = "kim";
		BookSaveReqDto dto = new BookSaveReqDto();
		dto.setTitle(title);
		dto.setAuthor(author);
		
		//stub
		when(bookRepository.save(any())).thenReturn(dto.toEntity());
		when(mailSender.send()).thenReturn(true);
		
		// when
		BookResponseDto bookResponseDto =  bookService.saveBook(dto);
		
		// then
		assertThat(dto.getTitle()).isEqualTo(bookResponseDto.getTitle());
		assertThat(dto.getAuthor()).isEqualTo(bookResponseDto.getAuthor());
	}
	
	@Test
	public void findAll() {
		// given
		// stub
		List<Book> books = new ArrayList<>();
		books.add(new Book(1L, "title1", "kim1"));
		books.add(new Book(2L, "title2", "kim2"));
		
		when(bookRepository.findAll()).thenReturn(books);
		
		// when
		List<BookResponseDto> responseDtos = bookService.findAll();
		
		// then
		assertThat(responseDtos.get(0).getTitle()).isEqualTo("title1");
		assertThat(responseDtos.get(0).getAuthor()).isEqualTo("kim1");
		assertThat(responseDtos.get(1).getTitle()).isEqualTo("title2");
		assertThat(responseDtos.get(1).getAuthor()).isEqualTo("kim2");
	}
	
	@Test
	public void findOne() {
		// given
		Long id = 1L;
		String title = "title1";
		String author = "author1";
		Book book = Book.builder()
						.id(id)
						.title(title)
						.author(author)
						.build();
		
		// stub
		when(bookRepository.findById(id)).thenReturn(Optional.of(book));
		
		// when
		BookResponseDto dto = bookService.findOne(id);
		
		
		// then
		assertThat(dto.getId()).isEqualTo(id);
		assertThat(dto.getTitle()).isEqualTo(title);
		assertThat(dto.getAuthor()).isEqualTo(author);
	}
}
