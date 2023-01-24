package com.demo.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.demo.myapp"})
public class MyApp {
	
	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
	}

}
