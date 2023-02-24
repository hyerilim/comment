package com.example.demo.question;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.user.SiteUserEntity;
import com.example.demo.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 	// final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@Controller
public class QuestionController {
	
	private final QuestionService questionService;
	private final UserService userService;
	
	//페이징 처리와 함께
	@GetMapping(value="/question/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="1") int page, @RequestParam(value = "keyword", defaultValue = "") String keyword, @RequestParam(value = "columns", defaultValue = "subject") String columns) {
		Page<QuestionEntity> paging = this.questionService.getList(page, keyword, columns);

		model.addAttribute("paging", paging);  
        model.addAttribute("keyword", keyword);
        model.addAttribute("columns", columns);
		return "jsp/question_list";
	}
	
	
	@GetMapping(value="/question/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		QuestionEntity question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
		return "jsp/question_detail";
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/question/create")
	public String questionCreate(QuestionFormDTO questionFormDTO) {
		return "jsp/question_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/question/create")
	public String questionCreate(@Valid QuestionFormDTO questionFormDTO, BindingResult bindingResult
			, Model model
			, @RequestParam("images") List<MultipartFile> images
			, Principal principal) {

		//List<MultipartFile>
		
		// multipartfile 변수는 form과 일치시켜 준다. 여러장을 넣고싶으면 arraylist필요
		//업로드될 실제경로
		// String realPath= request.getServletContext().getRealPath("/WEB-INF/views/save");
		URL url= this.getClass().getClassLoader().getResource("static/save");
		String realPath=url.getPath();
		
		System.out.println(realPath);
		
		List<String> fileList = new ArrayList<>();
		
		for(int i = 0; i < images.size(); i++) {
		// 파일명 랜덤하게 
		UUID uuid = UUID.randomUUID();		
		String uploadname= uuid + images.get(i).getOriginalFilename();
		
		fileList.add(uploadname);
		}
			//실제업로드
			try {
				for(int i = 0; i < images.size(); i++) {	
				File uploadFile = new File(realPath + "\\" + fileList.get(i));
				images.get(i).transferTo(uploadFile);
				}
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				for(int i = 0; i < images.size(); i++) {	
				new File(realPath + "\\" + fileList.get(i)).delete();
				}
			}

		//System.out.println(images.getOriginalFilename().getClass().getSimpleName());
		//System.out.println(images.getResource());
	
		/*//에러 처리
		 * if(bindingResult.hasErrors()) { return "redirect:/question/create"; }
		 */
		
		//파일 없을때 DB에 null로 저장
		//if(images.getOriginalFilename()==null || images.getOriginalFilename()==""){
			//uploadname= null;
		//}
		System.out.println(fileList.get(0));
		System.out.println(fileList);
		
		// model.addAttribute("fileList", fileList);
		
		
		SiteUserEntity siteUser = this.userService.getUser(principal.getName());
		
		String imagesList = fileList.toString();
		
		this.questionService.create(questionFormDTO.getSubject(), questionFormDTO.getContent(), imagesList, siteUser);
		return "redirect:/question/list";	// 질문 저장 후 질문목록으로 이동
	}
	
	
	//수정 페이지
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/question/modify/{id}")
    public String questionModify(Model model, @PathVariable("id") Integer id) {
       
    	QuestionEntity question = this.questionService.getQuestion(id);
        
//        URL url= this.getClass().getClassLoader().getResource("static/save");
//		String realPath=url.getPath();
//        if(question.getImages() != null) {
//        	File file = new File(realPath+"\\"+question.getImages());
//        	file.delete();
//        }
        
        model.addAttribute("images", question.getImages());
        model.addAttribute("subject", question.getSubject());
        model.addAttribute("content", question.getContent());
        //model.addAttribute("images", question.getImages());
        
        return "jsp/question_form";
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/question/modify/{id}")
    public String questionModify(@Valid QuestionFormDTO questionFormDTO, BindingResult bindingResult
    		, @PathVariable("id") Integer id
    		, @RequestParam("images") List<MultipartFile> images
    		, Model model) {
    	
    
    	List<String> fileList = new ArrayList<>();
    	String uploadname = "";
   
		URL url= this.getClass().getClassLoader().getResource("static/save");
		String realPath=url.getPath();
		
		System.out.println(realPath);
		
		for(int i = 0; i < images.size(); i++) {
		// 파일명 랜덤하게 
		UUID uuid = UUID.randomUUID();		
		uploadname= uuid + images.get(i).getOriginalFilename();
		
		fileList.add(uploadname);
		}
			//실제업로드
			try {
				for(int i = 0; i < images.size(); i++) {	
				File uploadFile = new File(realPath + "\\" + fileList.get(i));
				images.get(i).transferTo(uploadFile);
				}
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				for(int i = 0; i < images.size(); i++) {	
				new File(realPath + "\\" + fileList.get(i)).delete();
				}
			}
		
		model.addAttribute("fileList", fileList.get(0));
 
    	

	
        QuestionEntity question = this.questionService.getQuestion(id);

        this.questionService.modify(question, questionFormDTO.getSubject(), questionFormDTO.getContent(), fileList.toString());
        return String.format("redirect:/question/detail/%s", id);
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/question/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
        QuestionEntity question = this.questionService.getQuestion(id);

        this.questionService.delete(question);
        return "redirect:/question/list";
    }
    
}
