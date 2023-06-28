package com.blogApplication.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blogApplication.entity.User;
import com.blogApplication.exception.ResourseNotFoundException;
import com.blogApplication.playloads.UserDto;
import com.blogApplication.repository.UserRepo;
import com.blogApplication.services.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	
	@Autowired 
	ModelMapper modelMapper;
	@Autowired
	UserRepo  repo;

	@Override
	public UserDto createUser(UserDto user) {
		User userc= userdtoTouser(user);
			this.repo.save(userc);
		 UserDto userDto=userTouserdto(userc);
		 return userDto;		
	}

	@Override
	public UserDto updateUer(UserDto user, int user_id) {
		User useru=this.repo.findById(user_id).orElseThrow(()->new ResourseNotFoundException("user","of", user_id));
		useru.setUserId(user.getUserId());
		useru.setPassword(user.getPassword());
		useru.setName(user.getName());
		useru.setEmail(user.getEmail());
		useru.setAbout(user.getAbout());
		this.repo.save(useru);
		
		UserDto userDto=userTouserdto(useru);
		return userDto;
		
	
	}

	@Override
	public UserDto getUserById(int user_id) {
		User userg=this.repo.findById(user_id).orElseThrow(()->
							new ResourseNotFoundException("user", "of", user_id));
		UserDto userDto=userTouserdto(userg);
		return userDto;
	}

	@Override
	public List<UserDto> getALlUser() {
		List<User>alluser=this.repo.findAll();
		List<UserDto>alluserdtoDtos=alluser.stream().map(user-> userTouserdto(user)).collect(Collectors.toList());
		return alluserdtoDtos;
	}

	@Override
	public void deleteUser(int user_id) {
		User user=this.repo.findById(user_id).orElseThrow(()->new ResourseNotFoundException("user","of", user_id));
		this.repo.delete(user);	
				
	}
	
	//modelmaper
	
	public User userdtoTouser(UserDto userdto) {
		User user=modelMapper.map(userdto, User.class);
		return user;
		
		
	}
	//modelmaper

	public UserDto userTouserdto(User user) {
		UserDto userdto=modelMapper.map(user, UserDto.class);
		return userdto;
		
		
	}
	

}
