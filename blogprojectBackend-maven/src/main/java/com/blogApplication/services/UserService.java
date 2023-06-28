package com.blogApplication.services;

import java.util.List;

import com.blogApplication.playloads.UserDto;

public interface UserService {
     UserDto createUser(UserDto user);
     UserDto updateUer(UserDto user , int user_id);
     UserDto getUserById(int user_id);
     List<UserDto> getALlUser();
     void deleteUser(int user_id);
     
}
