package com.vinetblog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vinetblog.Entity.Category;
import com.vinetblog.Entity.Post;
import com.vinetblog.Entity.User;
import com.vinetblog.exceptions.ResourceNotFoundException;
import com.vinetblog.payload.PostDto;
import com.vinetblog.payload.PostResopnse;
import com.vinetblog.repositry.CategorRepo;
import com.vinetblog.repositry.PostRepo;
import com.vinetblog.repositry.UserRepo;
import com.vinetblog.service.PostService;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategorRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categorid) {
		// TODO Auto-generated method stub
		
		User user =this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "user Id", userId));
		
		Category category = this.categoryRepo.findById(categorid).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categorid));
		
		
		Post post =this.modelMapper.map(postDto, Post.class);
		post.setImagename("default.png");
		post.setAddeddate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postRepo.save(post) ;
		
		return this.modelMapper.map(newPost, PostDto.class);
	}
	
	
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post =this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		post.setTittle(postDto.getTittle());;
		post.setContent(postDto.getContent());
		post.setImagename(postDto.getImagename());
		
		
		// TODO Auto-generated method stub
		
		return this.modelMapper.map(this.postRepo.save(post), PostDto.class);
	} 
	

	


	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post =this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		this.postRepo.delete(post);
		
		
		
	}

	@Override
	public PostResopnse getAllPost(Integer pageNumber, Integer pageSize,String sortBy) {
		// TODO Auto-generated method stub
		
		Pageable p = PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(sortBy));
		
		
		Page<Post> pagePosts = this.postRepo.findAll(p);
		List<Post>posts = pagePosts.getContent();
		
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResopnse postResponse = new PostResopnse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalpages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());
		
		
		return postResponse;
	}

	@Override
	public PostDto postById(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "cid", categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
		// TODO Auto-generated method stub
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;


	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "uid", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyWord) {
		// TODO Auto-generated method stub
		
		List<Post> posts= this.postRepo.searchByTittleContaining("%"+keyWord+"%");
		
		return  posts.stream().map((post)-> this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
	}







	

	

}
