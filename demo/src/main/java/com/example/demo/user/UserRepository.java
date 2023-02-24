package com.example.demo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUserEntity, Long> {

	// 사용자를 조회하는 기능
	Optional<SiteUserEntity> findByusername(String username);
}
