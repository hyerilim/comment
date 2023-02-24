package com.example.demo.question;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.DataNotFoundException;
import com.example.demo.answer.AnswerEntity;
import com.example.demo.user.SiteUserEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;

    public List<QuestionEntity> getList() {
        return this.questionRepository.findAll();
    }
    
    
    //id 값으로 Question 데이터를 조회하는 getQuestion 메서드, 리포지터리로 얻은 QuestionEntity 객체는 Optional 객체
    // Optional<T>는 null이 올수 있는 값을 감싸는 Wrapper클래스
    public QuestionEntity getQuestion(Integer id) { 
        Optional<QuestionEntity> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
    
    
    public void create(String subject, String content, String images, SiteUserEntity user) {
    	QuestionEntity q = new QuestionEntity();
    	q.setSubject(subject);
    	q.setContent(content);
    	q.setImages(images);
    	String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    	//q.setCreateDate(LocalDateTime.now());
    	q.setCreateDate(formatDate);
    	q.setAuthor(user);
    	this.questionRepository.save(q);
    }
    
    
    public Page<QuestionEntity> getList(int page, String keyword, String columns) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        
        int pageNum = page-1;
        Pageable pageable = PageRequest.of(pageNum, 10, Sort.by(sorts));
        
        Specification<QuestionEntity> spec = search(columns, keyword);
        return this.questionRepository.findAll(spec , pageable);
    }
   
    
    //정교한 쿼리의 작성을 도와주는 JPA의 도구 - Specification
    private Specification<QuestionEntity> search(String columns, String keyword) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<QuestionEntity> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거 
                Join<QuestionEntity, SiteUserEntity> u1 = q.join("author", JoinType.LEFT);
                Join<QuestionEntity, AnswerEntity> a = q.join("answerList", JoinType.LEFT);
                Join<AnswerEntity, SiteUserEntity> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get(columns), "%" + keyword + "%"), // 제목 
                        cb.like(q.get(columns), "%" + keyword + "%"),      // 내용

                        cb.like(u1.get("username"), "%" + keyword + "%"),    // 질문 작성자 
                        cb.like(a.get("content"), "%" + keyword + "%"),      // 답변 내용 
                        cb.like(u2.get("username"), "%" + keyword + "%"));   // 답변 작성자 
            }
        };
    }
    
    
    public void modify(QuestionEntity question, String subject, String content, String images) {
        question.setSubject(subject);
        question.setContent(content);
        question.setImages(images);
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        question.setModifyDate(formatDate);
        this.questionRepository.save(question);
    }
    
    
    public void delete(QuestionEntity question) {
        this.questionRepository.delete(question);
    }
    
}
