package com.example.demo.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateFormDTO userCreateFormDTO) {
		return "jsp/member/signup_form";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateFormDTO userCreateFormDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "jsp/member/signup_form";
        }
        userService.create(userCreateFormDTO.getUsername(), userCreateFormDTO.getEmail(), userCreateFormDTO.getPassword1());
        
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "jsp/member/login_form";
	}
	
	@GetMapping("/myPage")
	public String myPage() {
		
		return "jsp/member/my_page";
	}
	
}