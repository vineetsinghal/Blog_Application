package com.vinetblog.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
	private Integer categoryId;
	
	@NotBlank
	@Size(min=3)
	private String categoryTittle;
	
	@NotBlank
	private String categoryDescription;
	
	

}
