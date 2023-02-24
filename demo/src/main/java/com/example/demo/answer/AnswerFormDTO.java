package com.example.demo.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerFormDTO {
	
	@NotEmpty(message="내용을 필수항목 입니다.")
	private String content;
	
}
