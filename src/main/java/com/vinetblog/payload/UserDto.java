package com.vinetblog.payload;


import javax.validation.constraints.*;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	private int id;
	
	@NotBlank(message = "name is not vaild")
	@Size(min = 3)
	private String name;
	
	@Email 
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$" , message = "email not vaild")
	private String Email;
	
	@NotBlank
	@Size(min=3,max=10, message = "password not correct")
	private String password;
	
	@NotBlank
	private String about;

}
