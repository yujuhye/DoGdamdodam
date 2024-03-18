package com.dogdam.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
   
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      
      registry.addResourceHandler("/goodsUploadImg/**").addResourceLocations("file:///C://goods/upload/");
//             .addResourceLocations("file:///c://calender/upload/");
      
   }
   
   @Bean RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
   
}