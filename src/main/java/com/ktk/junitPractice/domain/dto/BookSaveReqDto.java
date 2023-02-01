package com.ktk.junitPractice.domain.dto;


import com.ktk.junitPractice.domain.entiry.Book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookSaveReqDto {
	
	@NotBlank
	@Size(min = 1, max = 50)
	private String title;
	
	@NotBlank
	@Size(min = 2, max = 20)
	private String author;
	
	public Book toEntity() {
		return Book.builder()
					.title(title)
					.author(author)
					.build();
	}
}
