package com.ktk.junitPractice.domain.entiry;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum MemberShipType {
	NAVER("네이버"),
	LINE("라인"),
	KAKAO("카카오");
	
	private final String companyName;
}
