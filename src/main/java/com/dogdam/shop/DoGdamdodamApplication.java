package com.dogdam.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.dogdam.shop.admin.AdminConfig;

@SpringBootApplication
@EnableConfigurationProperties(AdminConfig.class)
public class DoGdamdodamApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoGdamdodamApplication.class, args);
	}

}
