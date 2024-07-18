package com.TeleApps.spring_boot3_jwt_loginPage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringBoot3JwtLoginPageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot3JwtLoginPageApplication.class, args);
	}

}
