package com.ktk.junitPractice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberShipException extends RuntimeException {
	private final MemberShipError error;
}
