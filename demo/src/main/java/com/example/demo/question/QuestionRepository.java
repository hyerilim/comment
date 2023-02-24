package com.example.demo.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

//  리포지터리 - 엔티티에 의해 생성된 데이터베이스 테이블에 접근하는 메서드들(예: findAll, save 등)을 사용하기 위한 인터페이스, CRUD를 어떻게 처리할지 정의하는 계층
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
	QuestionEntity findBySubject(String subject);
	
	Page<QuestionEntity> findAll(Pageable pageable);

	
	// 검색
//	@Query("select "
//            + "distinct q "
//            + "from QuestionEntity q " 
//            + "left join SiteUserEntity u1 on q.author=u1 "
//            + "left join AnswerEntity a on a.question=q "
//            + "left join SiteUserEntity u2 on a.author=u2 "
//            + "where "
//           + "   :columns like %:keyword% "
//            + "   or q.content like %:keyword% "
//            + "   or u1.username like %:keyword% "
//            + "   or a.content like %:keyword% "
//            + "   or u2.username like %:keyword% "
//			)
//    Page<QuestionEntity> findAllByColumnAndKeyword(@Param("columns") String columns, @Param("keyword") String keyword, Pageable pageable);

	
	Page<QuestionEntity> findAll(Specification<QuestionEntity> spec, Pageable pageable);

}
