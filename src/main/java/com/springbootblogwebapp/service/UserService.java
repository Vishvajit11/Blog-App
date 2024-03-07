package com.springbootblogwebapp.service;



import com.springbootblogwebapp.dto.RegistrationDto;
import com.springbootblogwebapp.entity.User;

public interface UserService {
	
	void saveUser(RegistrationDto registrationDto);

	User findByEmail(String email);
	
}
