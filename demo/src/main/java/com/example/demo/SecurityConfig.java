package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration		// 스프링의 환경설정 파일
@EnableWebSecurity	// 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 //내부적으로 SpringSecurityFilterChain이 동작하여 URL 필터가 적용
@EnableMethodSecurity(prePostEnabled = true) //@PreAuthorize 애너테이션이 동작
public class SecurityConfig {

	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//모든 인증되지 않은 요청을 허락, 로그인을 하지 않더라도 모든 페이지에 접근 가능
		http.authorizeHttpRequests().requestMatchers(
				new AntPathRequestMatcher("/**")).permitAll()
		
		// 에러 해결 위해 csrf 생성 막음.
			.and()
				.csrf().disable()
		
		//스프링 시큐리티의 로그인 설정을 담당하는 부분, 로그인 페이지의 URL은 /user/login이고 로그인 성공시에 이동하는 디폴트 페이지는 루트 URL(/)
				.formLogin()
					.loginPage("/user/login")
						.defaultSuccessUrl("/")
		
		// 로그아웃을 위한 설정: 로그아웃 URL을 /user/logout으로 설정, 로그아웃이 성공하면 루트(/) 페이지로 이동, 로그아웃시 생성된 사용자 세션도 삭제
			.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
						.logoutSuccessUrl("/")
							.invalidateHttpSession(true)
							
    ;	
		return http.build();
	    }
	
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
   }
        
	// AuthenticationManager는 스프링 시큐리티의 인증을 담당, AuthenticationManager 빈 생성시 스프링의 내부 동작으로 인해 위에서 작성한 UserSecurityService와 PasswordEncoder가 자동으로 설정
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
    }

}
