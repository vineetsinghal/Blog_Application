package com.vinetblog.repositry;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vinetblog.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	
	Optional<User> findByEmail( String email);
	
}

