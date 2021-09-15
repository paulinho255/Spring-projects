package com.springboot.bookstore.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.springboot.bookstore.services.DBServices;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBServices dbServices;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean initDB() {
		if (this.strategy.equals("create")) {
			this.dbServices.instanciaDb();
		}
		return false;
	}
}
