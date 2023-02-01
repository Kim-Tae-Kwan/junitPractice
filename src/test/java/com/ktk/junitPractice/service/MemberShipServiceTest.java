package com.ktk.junitPractice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ktk.junitPractice.domain.dto.MemberShipAddDto;
import com.ktk.junitPractice.domain.dto.MemberShipRespDto;
import com.ktk.junitPractice.domain.entiry.MemberShip;
import com.ktk.junitPractice.domain.entiry.MemberShipType;
import com.ktk.junitPractice.exception.MemberShipError;
import com.ktk.junitPractice.exception.MemberShipException;
import com.ktk.junitPractice.repository.MemberShipRepository;

@ExtendWith(MockitoExtension.class)
class MemberShipServiceTest {
	
	@InjectMocks
	private MemberShipService memberShipService;
	
	@Mock
	private MemberShipRepository memberShipRepository;
	
	private MemberShipAddDto dto;
	
	@BeforeEach
	public void init() {
		dto = new MemberShipAddDto();
		dto.setUserId(1L);
		dto.setType(MemberShipType.NAVER);
	}
	
	
	@Test
	public void 멤버십등록실패_이미존재함() {
		// given
		
		// stub
		when(memberShipRepository.findByUserIdAndType(any(), any()))
		.thenReturn(Optional.of(MemberShip.builder().build()));
		
		// when
		MemberShipException exception = assertThrows(MemberShipException.class, () -> memberShipService.addMemberShip(dto));
		
		// then
		assertThat(exception.getError()).isEqualTo(MemberShipError.DUPLICATE_MEMBERSHIP_REGISTER);
	}
	
	@Test
	public void 멤버십등록성공() {
		// given
		MemberShip existMemberShip = MemberShip.builder()
											.id(1L)
											.userId(dto.getUserId())
											.type(dto.getType())
											.point(0)
											.createdAt(LocalDateTime.now())
											.updatedAt(LocalDateTime.now())
											.build();
		
		// stub
		when(memberShipRepository.findByUserIdAndType(any(), any()))
			.thenReturn(Optional.empty());
		
		when(memberShipRepository.save(any())).thenReturn(existMemberShip);
		
		// when
		MemberShipRespDto respDto = memberShipService.addMemberShip(dto);
		
		// then
		assertThat(respDto.getId()).isNotNull();
		assertThat(respDto.getUserId()).isEqualTo(dto.getUserId());
		assertThat(respDto.getType()).isEqualTo(dto.getType().toString());
		assertThat(respDto.getPoint()).isEqualTo(0);
	}
	
}
