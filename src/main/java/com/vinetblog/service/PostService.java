package com.vinetblog.service;

import java.util.List;

import com.vinetblog.Entity.Post;
import com.vinetblog.payload.PostDto;
import com.vinetblog.payload.PostResopnse;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categorid);
	
	PostDto updatePost(PostDto postDto, Integer postId );
	
	void deletePost(Integer postId);
	
	PostResopnse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);
	
	PostDto postById(Integer postId);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyWord);
	

}
