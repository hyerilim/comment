package com.example.demo.user;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
    
	public SiteUserEntity create(String username, String email, String password) {
		SiteUserEntity user = new SiteUserEntity();
        user.setUsername(username);
        user.setEmail(email);
// BCryptPasswordEncoder는 BCrypt 해싱 함수(BCrypt hashing function)를 사용해서 비밀번호를 암호화
// PasswordEncoder 빈(bean)으로 등록해서 사용
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        //user.setPassword(password);
        this.userRepository.save(user);
        return user;
	}
	
	public SiteUserEntity getUser(String username) {
		Optional<SiteUserEntity> siteUser = this.userRepository.findByusername(username);
		if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
	}
	
}
