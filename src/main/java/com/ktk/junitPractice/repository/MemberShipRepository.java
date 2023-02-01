package com.ktk.junitPractice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktk.junitPractice.domain.entiry.MemberShip;
import com.ktk.junitPractice.domain.entiry.MemberShipType;

@Repository
public interface MemberShipRepository extends JpaRepository<MemberShip, Long> {
	
	Optional<MemberShip> findByUserIdAndType(Long userId, MemberShipType type);
}
