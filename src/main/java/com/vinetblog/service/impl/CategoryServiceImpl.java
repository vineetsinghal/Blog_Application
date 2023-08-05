package com.vinetblog.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinetblog.Entity.Category;
import com.vinetblog.exceptions.ResourceNotFoundException;
import com.vinetblog.payload.CategoryDto;
import com.vinetblog.repositry.CategorRepo;
import com.vinetblog.service.CategorService;

@Service
public class CategoryServiceImpl implements CategorService{
	
	@Autowired
	private CategorRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelPaper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		
		Category cat = this.modelPaper.map(categoryDto, Category.class);
		Category createdcat=this.categoryRepo.save(cat);
		return this.modelPaper.map(createdcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer catid) {
		Category cat= this.categoryRepo.findById(catid).orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", catid));
		// TODO Auto-generated method stub
		cat.setCategoryTittle(categoryDto.getCategoryTittle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		return this.modelPaper.map(this.categoryRepo.save(cat), CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer catid) {
		// TODO Auto-generated method stub
		Category cat= this.categoryRepo.findById(catid).orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", catid));
		this.categoryRepo.deleteById(catid);
		return;
	}

	@Override
	public CategoryDto getCategory(Integer catid) {
		// TODO Auto-generated method stub
		Category cat= this.categoryRepo.findById(catid).orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", catid));
		return this.modelPaper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		// TODO Auto-generated method stub
		List<Category> categories = this.categoryRepo.findAll();
		return categories.stream().map((t) -> this.modelPaper.map(t, CategoryDto.class)).collect(Collectors.toList());
		
	}

}
