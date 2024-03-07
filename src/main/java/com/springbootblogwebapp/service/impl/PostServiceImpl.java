package com.springbootblogwebapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.springbootblogwebapp.repository.PostRepository;
import com.springbootblogwebapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.springbootblogwebapp.dto.PostDto;
import com.springbootblogwebapp.entity.Post;
import com.springbootblogwebapp.entity.User;
import com.springbootblogwebapp.mapper.PostMapper;
import com.springbootblogwebapp.service.PostService;
import com.springbootblogwebapp.util.SecurityUtils;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	private UserRepository userRepository;
	
	// Constructor based dependency
	public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<PostDto> findallPosts() {
		List<Post> posts = postRepository.findAll();
		// to convert List<Post> to List<PostDto>
//		return posts.stream().map((post) -> PostMapper.mapToPostDto(post))
//				.collect(Collectors.toList());
		
		
		return posts.stream().map(PostMapper::mapToPostDto)
				.collect(Collectors.toList());
	}

	@Override
	public void createPost(PostDto postDto) {
		// this email and user is to map user id in post table
		String email = SecurityUtils.getCurrentUser().getUsername();
		User user = userRepository.findByEmail(email);
		Post post = PostMapper.mapToPost(postDto);
		post.setCreatedBy(user);
		postRepository.save(post);
	}

	@Override
	public PostDto findPostById(Long postId) {
		Post post = postRepository.findById(postId).get();
		return PostMapper.mapToPostDto(post);
		
	}

	@Override
	public void updatePost(PostDto postDto) {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		
		Post post = PostMapper.mapToPost(postDto);
		post.setCreatedBy(createdBy);
		postRepository.save(post); // save method can be used for both update and insert
		                           // if post object in DB has id (PK) then it will update it else insert new
	}

	@Override
	public void deletePost(Long postId) {
		postRepository.deleteById(postId);
	}

	@Override
	public PostDto findPostByUrl(String postUrl) {
		Post post = postRepository.findByUrl(postUrl).get();
		return PostMapper.mapToPostDto(post);
		
	}

	@Override
	public List<PostDto> searchPosts(String query) {
		List<Post> posts = postRepository.searchPosts(query);
		return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
	}

	// this method finds posts according to logged in user
	@Override
	public List<PostDto> findPostByUser() {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Long userId = createdBy.getId();
		List<Post> posts = postRepository.findPostByUser(userId);
		
		return posts.stream().map((post) -> PostMapper.mapToPostDto(post)).collect(Collectors.toList()); 
	}

}
