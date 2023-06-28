package com.blogApplication.seurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blogApplication.entity.User;
import com.blogApplication.repository.UserRepo;

public class CustomUserDetails implements UserDetailsService{
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		this.userRepo.findByEmail(username);
	
		// TODO: handle exception
	}
	

}
