package com.vinetblog.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinetblog.Entity.Category;
import com.vinetblog.Entity.User;

public interface CategorRepo extends JpaRepository<Category, Integer> {
	
	
	
	

}
