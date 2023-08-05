package com.vinetblog.service;

import com.vinetblog.payload.UserDto;

import java.util.*;

public interface UserServices {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer id);
	void deleteUser(Integer id);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUsr();
	

}
