package com.ktk.junitPractice.domain.entiry;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ktk.junitPractice.domain.dto.MemberShipRespDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class MemberShip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long userId;
	
	@Enumerated(EnumType.STRING)
	private MemberShipType type;
	
	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer point;
	
	@CreationTimestamp
	@Column(nullable = false, length = 20, updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(length = 20)
	private LocalDateTime updatedAt;
	
	public MemberShipRespDto toDto() {
		return MemberShipRespDto.builder()
								.id(id)
								.userId(userId)
								.type(type.toString())
								.point(point)
								.build();
	}
}
