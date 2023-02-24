package com.example.demo.answer;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.question.QuestionEntity;
import com.example.demo.question.QuestionService;
import com.example.demo.user.SiteUserEntity;
import com.example.demo.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id
								, @Valid AnswerFormDTO answerFormDTO
								, BindingResult bindingResult
								, Principal principal){		// principal 객체를 사용 - 로그인한 사용자의 사용자명을 통해 SiteUser객체를 조회
		QuestionEntity question = this.questionService.getQuestion(id);
		SiteUserEntity siteUser = this.userService.getUser(principal.getName());
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "jsp/question_detail";
		}
		
		AnswerEntity answer = this.answerService.create(question, answerFormDTO.getContent(), siteUser);
		return String.format("redirect:/question/detail/%s#answer_%s", id, answer.getId());
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String answerModify(AnswerFormDTO answerFormDTO, @PathVariable("id") Integer id, Model model, Principal principal) {
		AnswerEntity answer = this.answerService.getAnswer(id);
		
		model.addAttribute("content", answer.getContent());
		
		answerFormDTO.setContent(answer.getContent());
		return "jsp/answer_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String answerModify(@Valid AnswerFormDTO answerFormDTO, @PathVariable("id") Integer id, Principal principal) {
		AnswerEntity answer = this.answerService.getAnswer(id);
		
		this.answerService.modify(answer, answerFormDTO.getContent());
		return String.format("redirect:/question/detail/%s#answer_%s", answer.getQuestion().getId(), answer.getId());
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String answerDelete( @PathVariable("id") Integer id) {
		AnswerEntity answer = this.answerService.getAnswer(id);
		
		this.answerService.delete(answer);
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	}
	
}
