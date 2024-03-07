package com.springbootblogwebapp.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Entity
@Table(name="posts")
public class Post {
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	private String url;
	
	@Lob        // content should be able to hold more data
	@Column(nullable = false, columnDefinition = "longtext")
	private String content;
	
	@Lob
	@Column(nullable = false, columnDefinition = "longtext")
	private String shortDescription;
	
	@CreationTimestamp     //(hibernate annotation)automaticatically assign time when we create object of post
	private LocalDateTime createdOn;
	
	@UpdateTimestamp       // (hibernate will take care of this fields we don't need to manually assign value )
	private LocalDateTime updatedOn;
	
	@ManyToOne
	@JoinColumn(name = "createdBy", nullable = false)
	private User createdBy; // primary key Users table becomes foreign key in post table
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)  // (mappedBy)we need to tell hibernate that we are using bidirectional mapping and post is the owner of this relationship
	private Set<Comment> comments = new HashSet<>();             // (cascasetype.remove) whenever we delete a blog post hibernate should delete its comments also
		
}
