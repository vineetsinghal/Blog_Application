package com.vinetblog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogappisApplication implements CommandLineRunner {
	
	
	@Autowired
	private PasswordEncoder passwordencode;

	public static void main(String[] args) {
		SpringApplication.run(BlogappisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelpaper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.passwordencode.encode("dvwd"));
	}

}
