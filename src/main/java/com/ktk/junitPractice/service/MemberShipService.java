package com.ktk.junitPractice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ktk.junitPractice.domain.dto.MemberShipAddDto;
import com.ktk.junitPractice.domain.dto.MemberShipRespDto;
import com.ktk.junitPractice.domain.entiry.MemberShip;
import com.ktk.junitPractice.exception.MemberShipError;
import com.ktk.junitPractice.exception.MemberShipException;
import com.ktk.junitPractice.repository.MemberShipRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberShipService {

	private final MemberShipRepository memberShipRepository;
	
	public MemberShipRespDto addMemberShip(MemberShipAddDto dto) {

		// 이미 존재 검증
		Optional<MemberShip> memberShipOP = memberShipRepository.findByUserIdAndType(dto.getUserId(), dto.getType());
		
		if(memberShipOP.isPresent()) throw new MemberShipException(MemberShipError.DUPLICATE_MEMBERSHIP_REGISTER);
		else {
			return memberShipRepository.save(dto.toEntity()).toDto();
		}
	}
	

}
