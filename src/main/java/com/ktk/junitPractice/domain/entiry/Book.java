package com.ktk.junitPractice.domain.entiry;

import com.ktk.junitPractice.domain.dto.BookResponseDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String title;
	
	@Column(length = 30, nullable = false)
	private String author;
	
	public BookResponseDto toDto() {
		return BookResponseDto.builder()
								.id(id)
								.title(title)
								.author(author)
								.build();
	}
}
