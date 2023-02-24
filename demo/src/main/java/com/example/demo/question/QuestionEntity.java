package com.example.demo.question;

import java.util.List;

import com.example.demo.answer.AnswerEntity;
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
@Table(name="questions")
public class QuestionEntity {
	   
	@Id		// 기본 키(primary key)
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// strategy -고유번호 생성, GenerationType.IDENTITY -해당 컬럼만의 시퀀스를 생성 번호를 증가
	private Integer id;			// 질문의 고유 번호

	@Column(length = 200)
	private String subject;		// 질문의 제목
	
	@Column(length = 1000)
	private String content;		// 질문의 내용
	
	private String createDate;		// 질문을 작성한 일시
	
	private String modifyDate;		// 수정 일시
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<AnswerEntity> answerList;
	
	// site_user 테이블의 id 값이 저장되어 SiteUser 엔티티와 연결
	@ManyToOne
    private SiteUserEntity author;
	
	private String images;
	
}

// 테이블 컬럼명 - 카멜케이스(Camel Case) 이름은 모두 소문자로 변경되고 언더바(_)로 단어가 구분

