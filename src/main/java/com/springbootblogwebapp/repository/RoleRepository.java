package com.springbootblogwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootblogwebapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);
}
