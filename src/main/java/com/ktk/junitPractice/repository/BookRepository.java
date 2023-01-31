package com.ktk.junitPractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktk.junitPractice.domain.entiry.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
