package com.example.demo.answer;

import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.example.demo.comment.CommentEntity;
import com.example.demo.question.QuestionEntity;
import com.example.demo.user.SiteUserEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="answers")
public class AnswerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 1000)
	private String content;
	 
	@CreatedDate
	private String createDate;
	
	private String modifyDate;
	
	@ManyToOne
	private QuestionEntity question;
	
	
	// site_user 테이블의 id 값이 저장되어 SiteUser 엔티티와 연결
	@ManyToOne
    private SiteUserEntity author;
	
	// 댓글 리스트를 참조, Answer 연결하기 위한 속성명이 answer이므로 mappedBy의 값 "answer" 전달
	@OneToMany(mappedBy = "answer", cascade = CascadeType.REMOVE)
    private List<CommentEntity> commentList;
}
