package com.blogApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApplication.entity.User;

public interface UserRepo extends JpaRepository<User, Integer  >  {
		User findByEmail(String email);
}
