package com.gestion.fibrolaser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication				//SE AGREGO LA HERENCIA DE SPRING SERVLET INITIALIZER
public class FibrolaserApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FibrolaserApplication.class, args);
	}

	//SE AGREGO EL OVERRIDE DEL METODO PARA PODER IMPLEMENTAR EN UN SERVIDOR TOMCAT
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder aplication) {
		return  aplication.sources(FibrolaserApplication.class);
	}
}
