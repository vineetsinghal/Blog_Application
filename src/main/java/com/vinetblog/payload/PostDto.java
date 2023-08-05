package com.vinetblog.payload;

import java.util.Date;

import com.vinetblog.Entity.Category;
import com.vinetblog.Entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private String tittle;
	private String content;
	
	private String imagename;

	private Date addeddate;
	
	private CategoryDto category;
	private UserDto user;

}
