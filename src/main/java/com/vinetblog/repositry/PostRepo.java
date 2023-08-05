package com.vinetblog.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vinetblog.Entity.Category;
import com.vinetblog.Entity.Post;
import com.vinetblog.Entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.tittle like : key")
	List<Post> searchByTittleContaining(@Param("key") String title);
	
	
	

}
