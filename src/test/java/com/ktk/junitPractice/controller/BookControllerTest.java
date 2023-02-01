package com.ktk.junitPractice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.matches;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.ktk.junitPractice.domain.dto.BookSaveReqDto;
import com.ktk.junitPractice.service.BookService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BookControllerTest {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private TestRestTemplate rt;
	
	private static HttpHeaders headers;
	private static ObjectMapper mapper;
	
	@BeforeAll
	public static void init() {
		mapper = new ObjectMapper();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	}
	
	@Test
	public void saveBook() throws JsonProcessingException {
		// given
		BookSaveReqDto bookSaveReqDto = new BookSaveReqDto();
		bookSaveReqDto.setTitle("junit");
		bookSaveReqDto.setAuthor("kim");
		 
		String body = mapper.writeValueAsString(bookSaveReqDto);
		
		HttpEntity<String> request = new HttpEntity<String>(body, headers);
		
		//when
		ResponseEntity<String> response = rt.exchange("/api/v1/book", HttpMethod.POST, request, String.class);
		
		// then
		DocumentContext dc = JsonPath.parse(response.getBody());
		String title = dc.read("$.title");
		String author = dc.read("$.author");
		
		assertThat(title).isEqualTo("junit");
		assertThat(author).isEqualTo("kim");
	}
}
