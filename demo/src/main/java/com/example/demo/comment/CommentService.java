package com.example.demo.comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.answer.AnswerEntity;
import com.example.demo.user.SiteUserEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public CommentEntity create(AnswerEntity answer, String content, SiteUserEntity author) {
		CommentEntity comment = new CommentEntity();
		
		comment.setContent(content);
		String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		comment.setCreateDate(formatDate);
		comment.setAnswer(answer);
		comment.setAuthor(author);
		
		comment= this.commentRepository.save(comment);
		return comment;
	}
	
	public Optional<CommentEntity> getComment(Integer id) {
		
		return this.commentRepository.findById(id);
	}
	
	public CommentEntity modify(CommentEntity comment, String content) {
		comment.setContent(content);
		String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		comment.setModifyDate(formatDate);
		comment = this.commentRepository.save(comment);
		return comment;
	}
	
	public void delete(CommentEntity comment) {
		this.commentRepository.delete(comment);
	}
	
}
