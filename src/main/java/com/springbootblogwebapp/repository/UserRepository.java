package com.springbootblogwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootblogwebapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
}
