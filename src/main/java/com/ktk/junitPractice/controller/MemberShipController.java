package com.ktk.junitPractice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktk.junitPractice.domain.dto.MemberShipAddDto;
import com.ktk.junitPractice.util.MemberShipConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/memberShip")
public class MemberShipController {

	@PostMapping
	public ResponseEntity<?> addMemberShip(
			@RequestHeader(MemberShipConstants.USER_ID_HEADER) String userId, 
			@RequestBody @Valid MemberShipAddDto dto){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
}
