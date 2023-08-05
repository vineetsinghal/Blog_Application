package com.vinetblog.controller;

import java.awt.PageAttributes.MediaType;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vinetblog.Entity.Post;
import com.vinetblog.payload.ApiResponse;
import com.vinetblog.payload.PostDto;
import com.vinetblog.payload.PostResopnse;
import com.vinetblog.service.FileService;
import com.vinetblog.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postservice;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
		
		PostDto createPost=this.postservice.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> postDtos=this.postservice.getPostByUser(userId);
		
		
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
		
		
	}
	
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> postDtos=this.postservice.getPostByCategory(categoryId);
		
		
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResopnse> getAllPost(
			@RequestParam(value="pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue = "1",required = false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue = "postId", required = false) String sortBy
			){
		
		PostResopnse postResponse = this.postservice.getAllPost(pageNumber,pageSize,sortBy);
	
		return new ResponseEntity<PostResopnse>( postResponse, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}") 
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		return new ResponseEntity<PostDto>( this.postservice.postById(postId), HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ApiResponse DeletePost(@PathVariable Integer postId) {
		this.postservice.deletePost(postId);
		return new ApiResponse("Post is successfully deleted", true);
	}
	
	
	@PutMapping("/posts/{postId}")
	public  ResponseEntity<PostDto> UpdatePost(@RequestBody PostDto postDto,   @PathVariable Integer postId) {
		
		return new ResponseEntity<PostDto>( this.postservice.updatePost(postDto, postId), HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitlt(
			@PathVariable("keywords") String keywords){
		
		List<PostDto> result =this.postservice.searchPosts(keywords);
				return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
		
		
		
	}
	
	//PostImage metod
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostIamge(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
		PostDto postDto=this.postservice.postById(postId);
		String fileName=this.fileService.uploadImage(path, image);
		
		postDto.setImagename(fileName);
		PostDto updatedPostDto= this.postservice.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK );
		
		
	}
	
	@GetMapping(value = "post/image/{imageName}", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public void showImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		
		InputStream resource = this.fileService.getResource(path, imageName);
		
		response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);
		
		StreamUtils.copy(resource, response.getOutputStream());
		
		
		
	}
	

}
