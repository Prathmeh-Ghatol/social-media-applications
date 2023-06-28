package com.blogApplication.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.apiresponce.ApiResponce;
import com.blogApplication.playloads.UserDto;
import com.blogApplication.services.impl.UserServiceImpl;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserServiceImpl serviceImpl;
	
	
	@PostMapping("/")
	public ResponseEntity< UserDto> createUser(@Valid
			@RequestBody UserDto  user) {
		UserDto userdt =this.serviceImpl.createUser(user);
		return new ResponseEntity<UserDto>(userdt,HttpStatus.CREATED);			
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser( @PathVariable("userId") int id,@Valid @RequestBody UserDto user){
		UserDto userdto= this.serviceImpl.updateUer(user, id);
		return new ResponseEntity<UserDto>(userdto,HttpStatus.OK);
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") int id){
			UserDto   userDto=this.serviceImpl.getUserById(id);
			return new ResponseEntity<UserDto>(userDto,HttpStatus.FOUND);
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>getALlUser(){
		List<UserDto>userDtos=this.serviceImpl.getALlUser();
		return new ResponseEntity<List<UserDto>>(userDtos,HttpStatus.FOUND);
	}
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponce> deleteUser(@PathVariable("userId") int id) {
		
		this.serviceImpl.deleteUser(id);
		return new ResponseEntity<ApiResponce>(new ApiResponce("User Deleted" ,true),HttpStatus.OK);
		
	}
}