package com.blogApplication.playloads;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Component
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	
	
	
	
	
	private int userId;
	
	@NotEmpty
	@Size(min = 2 ,max = 30,message = "Please add with valid name")
	private String name;
	
	@Email(message = "Please enter vaild email")
	private String email;
	
	@NotEmpty
	@Size(min=5, max=12 ,message="Password must be in range of 5 to 12 charecter ")
	//@Pattern(regexp = "123457890 !@#$%^&*)abcdefghijlmnopqrstuvwxz")
	private String password;

	@NotEmpty()
	private String about;

}
