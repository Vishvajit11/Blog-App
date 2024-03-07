package com.springbootblogwebapp.service;

import java.util.List;

import com.springbootblogwebapp.dto.CommentDto;

public interface CommentService {
	
	void createComment(String postUrl, CommentDto commentdto);
	
	List<CommentDto> findAllComments();
	
	void deleteComment(Long id);
	
	List<CommentDto> findCommentsByPost();
}
