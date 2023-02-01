package com.ktk.junitPractice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ktk.junitPractice.domain.entiry.MemberShip;
import com.ktk.junitPractice.domain.entiry.MemberShipType;

@DataJpaTest
class MemberShipRepositoryTest {
	
	@Autowired
	private MemberShipRepository memberShipRepository;

	@Test
	void insert() {
		// given
		MemberShip memberShip = MemberShip.builder()
										.type(MemberShipType.NAVER)
										.userId(1L)
										.point(10000)
										.build();
		// when
		MemberShip result = memberShipRepository.save(memberShip);
		
		// then
		assertThat(result.getId()).isNotNull();
		assertThat(result.getUserId()).isEqualTo(1L);
		assertThat(result.getType()).isEqualTo(MemberShipType.NAVER);
		assertThat(result.getPoint()).isEqualTo(10000);
	}
	
	@Test
	public void existMemberShipWithUser() {
		// given
		MemberShip memberShip = MemberShip.builder()
											.type(MemberShipType.KAKAO)
											.userId(1L)
											.point(3000)
											.build();
		// when
		memberShipRepository.save(memberShip);
		MemberShip result = memberShipRepository.findByUserIdAndType(1L, MemberShipType.KAKAO).get();
		
		// then
		assertThat(result.getId()).isNotNull();
		assertThat(result.getType()).isEqualTo(MemberShipType.KAKAO);
		assertThat(result.getPoint()).isEqualTo(3000);
		assertThat(result.getCreatedAt()).isNotNull();
		assertThat(result.getUpdatedAt()).isNotNull();
	}

}
