package com.springbootblogwebapp.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.springbootblogwebapp.repository.CommentRepository;
import com.springbootblogwebapp.repository.PostRepository;
import com.springbootblogwebapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.springbootblogwebapp.dto.CommentDto;
import com.springbootblogwebapp.entity.Comment;
import com.springbootblogwebapp.entity.Post;
import com.springbootblogwebapp.entity.User;
import com.springbootblogwebapp.mapper.CommentMapper;
import com.springbootblogwebapp.service.CommentService;
import com.springbootblogwebapp.util.SecurityUtils;


@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private UserRepository userRepository;
	
	
	// constructor based dependency
	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}


	@Override
	public void createComment(String postUrl, CommentDto commentdto) {
		// we are retrieving this post entity because whenever we save
		// the comment object we need to also this post object to the
		// comment jpa entity because this is one to many relationship
		Post post = postRepository.findByUrl(postUrl).get();
		Comment comment = CommentMapper.mapToComment(commentdto);
		comment.setPost(post);
		commentRepository.save(comment);
		
		
		
	}


	@Override
	public List<CommentDto> findAllComments() {
		List<Comment> comments = commentRepository.findAll();
		return comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
	}


	@Override
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}


	@Override
	public List<CommentDto> findCommentsByPost() {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Long userId = createdBy.getId();
		List<Comment> comments = commentRepository.findCommentsByPost(userId);
		return comments.stream().map((comment) -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList()); 
	}

}
