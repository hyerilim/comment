package com.example.demo.comment;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentFormDTO {
	
	@NotEmpty
	private String content;
}
