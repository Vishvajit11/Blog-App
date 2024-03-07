package com.springbootblogwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springbootblogwebapp.dto.CommentDto;
import com.springbootblogwebapp.dto.PostDto;
import com.springbootblogwebapp.service.CommentService;
import com.springbootblogwebapp.service.PostService;

import jakarta.validation.Valid;

@Controller
public class CommentController {
	
	private CommentService commentService;
	private PostService postservice;
	
	// dependency
	public CommentController(CommentService commentService, PostService postservice) {
		this.commentService = commentService;
		this.postservice = postservice;
	}

	// ye niche vale handler method me humlog error form me error
	// check karke return kar rahe hai blog_post pe. but blog_post
	// me Post entity ke variables use kiye hai to hame vo
	// post find karke send karna hoga to display post content.
	
	
	//handler method to handle comment
		@PostMapping("/{postUrl}/comments")
		public String saveComment(@PathVariable String postUrl, @Valid @ModelAttribute("comment") CommentDto commentDto, BindingResult result, Model model) {
			PostDto postDto = postservice.findPostByUrl(postUrl);
			
			if(result.hasErrors()) {
				model.addAttribute("comment", commentDto);
				model.addAttribute("post", postDto);
				return "blog/blog_post";
			}
			commentService.createComment(postUrl, commentDto);
			//we have written a handler method to view post request
			// in that we have also added comment object
			// so we redirect to that handler method in blogcontroller
			return "redirect:/post/" + postUrl;
		}


}
