package com.vinetblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinetblog.Entity.User;
import com.vinetblog.payload.UserDto;
import com.vinetblog.repositry.UserRepo;
import com.vinetblog.service.UserServices;
import com.vinetblog.exceptions.*;
@Service

public class UserSericeImpl implements UserServices {
	
	@Autowired
	private UserRepo userRepo; 
	
	@Autowired
	private ModelMapper modelpaper;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=this.dtoToUser(userDto);
		User saveduser=this.userRepo.save(user);
		return this.usertoDto(saveduser);
		
		
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User"," id ", id));
		
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		User updateduser = this.userRepo.save(user);
		return this.usertoDto(updateduser);
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User"," id ", id));
		this.userRepo.delete(user);
		

	}

	@Override
	public UserDto getUserById(Integer id) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User"," id ", id));
		
		
		return this.usertoDto(user);
	}
	
	@Override 
	public List<UserDto> getAllUsr() {
		// TODO Auto-generated method stub
		List<User> users=this.userRepo.findAll();
		 List<UserDto>UserDtos=users.stream().map(user -> this.usertoDto(user)).collect(Collectors.toList()); 

		return UserDtos;
	}
	
	public User dtoToUser(UserDto userDto) {
		User user =this.modelpaper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setEmail(userDto.getEmail());
//		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}
	public UserDto usertoDto(User userDto) {
		UserDto user = this.modelpaper.map(userDto, UserDto.class); 
//		user.setId(userDto.getId());
//		user.setEmail(userDto.getEmail());
//		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}

}
