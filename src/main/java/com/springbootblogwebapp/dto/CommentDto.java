package com.springbootblogwebapp.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
	
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty(message="Email should not be empty or null")
	@Email
	private String email;
	@NotEmpty(message="Message body should not be empty")
	private String content;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
