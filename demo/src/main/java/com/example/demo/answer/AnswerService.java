package com.example.demo.answer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.question.QuestionEntity;
import com.example.demo.user.SiteUserEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;

	
	public AnswerEntity create(QuestionEntity question, String content, SiteUserEntity author) {
		AnswerEntity answer = new AnswerEntity();
		answer.setContent(content);   
		String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		//answer.setCreateDate(LocalDateTime.now());
		answer.setCreateDate(formatDate);
		answer.setQuestion(question);
		answer.setAuthor(author);
		this.answerRepository.save(answer);
		return answer;
	}
	
	
	public AnswerEntity getAnswer(Integer id) {
		Optional<AnswerEntity> answer = this.answerRepository.findById(id);
		
		return answer.get();
	}
	
	
	public void modify(AnswerEntity answer, String content) {
		answer.setContent(content);
		String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		answer.setModifyDate(formatDate);
		this.answerRepository.save(answer);
	}
	
	
	public void delete(AnswerEntity answer) {
		this.answerRepository.delete(answer);
	}
	
	
	
}
