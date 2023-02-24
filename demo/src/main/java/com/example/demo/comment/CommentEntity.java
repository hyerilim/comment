package com.example.demo.comment;

import com.example.demo.answer.AnswerEntity;
import com.example.demo.user.SiteUserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;			// 댓글 고유번호
	
	@ManyToOne
    private SiteUserEntity author;		// 댓글 작성자

	@Column
	private String content;
	
	@Column
	private String createDate;

	private String modifyDate;
	
	@ManyToOne
	private AnswerEntity answer;	// 이 댓글이 달린 답변
}
