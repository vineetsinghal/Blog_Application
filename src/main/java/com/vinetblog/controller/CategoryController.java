package com.vinetblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinetblog.payload.ApiResponse;
import com.vinetblog.payload.CategoryDto;
import com.vinetblog.service.CategorService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategorService categorSerivce;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto){
		return new ResponseEntity<CategoryDto>(this.categorSerivce.createCategory(categoryDto),HttpStatus.CREATED);
		
	}
	@PutMapping("/{catid}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto categoryDto, @PathVariable Integer catid){
		return new ResponseEntity<CategoryDto>(this.categorSerivce.updateCategory(categoryDto,catid),HttpStatus.OK);
		
	}
	
	@GetMapping("/{catid}")
	public ResponseEntity<CategoryDto> getCategory( @PathVariable Integer catid){
		return new ResponseEntity<CategoryDto>(this.categorSerivce.getCategory(catid),HttpStatus.OK);
		
	}
	@DeleteMapping("/{catid}")
	public ResponseEntity<ApiResponse> deleteCategory( @PathVariable Integer catid){
		this.categorSerivce.deleteCategory(catid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", false),HttpStatus.OK);
		
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategory( ){
		return ResponseEntity.ok(this.categorSerivce.getCategories());
		
	}
	
	
	
	
	
	
	
	
	

}
