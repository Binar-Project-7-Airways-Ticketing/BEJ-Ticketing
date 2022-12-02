package com.binar.bejticketing;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BejTicketingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BejTicketingApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public Cloudinary cloudinary(){
		return new Cloudinary();
	}


}
