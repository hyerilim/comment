package com.example.demo.comment;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.answer.AnswerEntity;
import com.example.demo.answer.AnswerService;
import com.example.demo.question.QuestionService;
import com.example.demo.user.SiteUserEntity;
import com.example.demo.user.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
    private CommentService commentService;
	
	@Autowired
    private QuestionService questionService;
	
	@Autowired
    private AnswerService answerService;

    @Autowired
    private UserService userService;
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/create/answer/{id}")
    public String createAnswerComment(CommentFormDTO commentFormDTO, Principal principal) {
    	return "jsp/comment_form";
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/create/answer/{id}")
    public String createAnswerComment(@PathVariable("id") Integer id, @Valid CommentFormDTO commentFormDTO, Principal principal) {
    	//Optional<Question> question = this.questionService.getQuestion(id);
        //Optional<SiteUser> user = this.userService.getUser(principal.getName());
    	//QuestionEntity question = this.questionService.getQuestion(id);
    	AnswerEntity answer = this.answerService.getAnswer(id);
    	SiteUserEntity siteUser = this.userService.getUser(principal.getName());
    	
    	this.commentService.create(answer, commentFormDTO.getContent(), siteUser);
  
    	return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modifyComment (Model model, CommentFormDTO commentFormDTO, @PathVariable("id") Integer id, Principal principal) {
    	Optional<CommentEntity> commentEntity = this.commentService.getComment(id);
    	CommentEntity comment = commentEntity.get();
    	
    	model.addAttribute("content", comment.getContent());
    	
    	return "jsp/comment_form";
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modifyComment (CommentFormDTO commentFormDTO, BindingResult bindingResult, @PathVariable("id") Integer id, Principal principal) {
    	
        if (bindingResult.hasErrors()) {
            return "jsp/comment_form";
        }
    	
    	Optional<CommentEntity> commentEntity = this.commentService.getComment(id);
    	CommentEntity comment = commentEntity.get();
    	
    	comment = this.commentService.modify(comment, commentFormDTO.getContent());
    	
    	return String.format("redirect:/question/detail/%s", comment.getAnswer().getQuestion().getId());
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable("id") Integer id, Principal principal) {
    	Optional<CommentEntity> commentEntity = this.commentService.getComment(id);
    	CommentEntity comment = commentEntity.get();
    	
    	this.commentService.delete(comment);
    	
    	return String.format("redirect:/question/detail/%s", comment.getAnswer().getQuestion().getId());
    }
    
}
