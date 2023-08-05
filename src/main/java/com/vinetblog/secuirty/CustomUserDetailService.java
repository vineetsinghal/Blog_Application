package com.vinetblog.secuirty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vinetblog.Entity.User;
import com.vinetblog.exceptions.ResourceNotFoundException;
import com.vinetblog.repositry.UserRepo;


@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		
		// TODO Auto-generated method stub
		//loading user from database from database
		
		User user=  this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("user ", username, 0));
		
		return user;
		
	}

}
