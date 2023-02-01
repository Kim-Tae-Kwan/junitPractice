package com.ktk.junitPractice.exception;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberShipError {
	DUPLICATE_MEMBERSHIP_REGISTER(HttpStatus.BAD_REQUEST, "Duplicated Membership Register Request"),
	;
	
	private final HttpStatus httpStatus;
	private final String message;
}
