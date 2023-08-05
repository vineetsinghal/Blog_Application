package com.vinetblog.service;

import java.util.List;

import com.vinetblog.payload.CategoryDto;

public interface CategorService {
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer catid);
	public void deleteCategory(Integer catid);
	public CategoryDto getCategory(Integer catid);
	public  List<CategoryDto> getCategories();
	
	
	

}
 