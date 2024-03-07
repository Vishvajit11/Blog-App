package com.springbootblogwebapp.service.impl;



import java.util.Arrays;

import com.springbootblogwebapp.repository.RoleRepository;
import com.springbootblogwebapp.repository.UserRepository;
import com.springbootblogwebapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springbootblogwebapp.dto.RegistrationDto;
import com.springbootblogwebapp.entity.Role;
import com.springbootblogwebapp.entity.User;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	// dependency
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public void saveUser(RegistrationDto registrationDto) {
		
		User user = new User();
		
		user.setName(registrationDto.getFirstName() + registrationDto.getLastName());
		user.setEmail(registrationDto.getEmail());
		// use spring security to encrypt the password
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		
		Role role = roleRepository.findByName("ROlE_GUEST");
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
	}



	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	
}
