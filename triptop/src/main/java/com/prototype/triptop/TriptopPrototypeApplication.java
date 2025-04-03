package com.prototype.triptop;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TriptopPrototypeApplication {

	public static Dotenv dotenv;
  
	public static void main(String[] args) {
    
		dotenv = Dotenv.load();

		SpringApplication.run(TriptopPrototypeApplication.class, args);

	}
}
