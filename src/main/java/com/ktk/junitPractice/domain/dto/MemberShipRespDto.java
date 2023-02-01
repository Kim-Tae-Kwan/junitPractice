package com.ktk.junitPractice.domain.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ktk.junitPractice.domain.entiry.MemberShipType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberShipRespDto {
	private Long id;
	private Long userId;
	private String type;
	private Integer point;
}
