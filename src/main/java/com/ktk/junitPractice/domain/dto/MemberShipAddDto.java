package com.ktk.junitPractice.domain.dto;

import com.ktk.junitPractice.domain.entiry.MemberShip;
import com.ktk.junitPractice.domain.entiry.MemberShipType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberShipAddDto {
	
	@NotNull
	@Size(min = 0)
	private Long userId;
	
	@NotNull
	private MemberShipType type;
	
	public MemberShip toEntity() {
		return MemberShip.builder()
						.userId(this.userId)
						.type(this.type)
						.build();
	}
}
