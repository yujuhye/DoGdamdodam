package com.dogdam.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.dogdam.shop.user.member.IUserMemberDaoForMybatis;
import com.dogdam.shop.user.member.MemberDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Autowired
	IUserMemberDaoForMybatis iUserMemberDaoForMybatis;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		log.info("passwordEncoder()");
		
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("filterChain()");
		
		http
		.cors((cors) -> cors.disable())
		.csrf((csrf) -> csrf.disable())
		.authorizeHttpRequests((request) -> request
				.requestMatchers(
						"/basket/**",
						"/donation/**",
						"/user/member/pet/**",
						"/user/member/modify_form",
						"/review/userReviewList",
						"/review/reviewInsertForm",
						"/review/insertReviewConfirm",
						"/review/reviewDetailView",
						"/review/reviewModifyForm",
						"/review/reviewModifyConfirm",
						"/review/reviewDelete",
						"/user/qa/**",
						"/user/bookmark/**"
						
						).authenticated()
				.requestMatchers(
						"/",
						"/css/**",
						"/img/**",
						"/js/**",
						"/user/member/**",
						"/admin/**",
                        "/user/goods/goodsDetailView",
                        "/user/goods/searchGoodsConfirm",
                        "/user/goods/searchCategory",
                        "/user/goods/searchProductsByCategory",
                        "/review/reviewList",
                        "/user/bookmark/bookmarkList",
                        "/user/goods/goodsList"
						).permitAll());
		
		
		http
		.formLogin(login -> login
				.loginPage("/user/member/login_form")
				.loginProcessingUrl("/user/member/login_confirm")
				.usernameParameter("u_id")
				.passwordParameter("u_pw")
				.successHandler((request, response, authentication) -> {
					log.info("success handler()");
					
					MemberDto memberDto = new MemberDto();
					memberDto.setU_id(authentication.getName());
					MemberDto loginedMemberDto = 
							iUserMemberDaoForMybatis.selectMemberForLogin(memberDto);
					
					HttpSession session = request.getSession();
					session.setAttribute("loginedMemberDto", loginedMemberDto);
					session.setMaxInactiveInterval(60 * 60);
					
					response.sendRedirect("/");
					
				})
				.failureHandler((request, response, exception) -> {
					log.info("fail handler()");
					log.info("erro:" + exception.getMessage());  
					
					response.sendRedirect("/user/member/login_form");
					
				})
				.permitAll()
				);
		
		
		http
		.logout(logout -> logout
				.logoutUrl("/user/member/logout_confirm")
				.logoutSuccessHandler((request, response, authentication) -> {
					log.info("logout success");
					
					HttpSession session = request.getSession();
					session.invalidate();
					
					response.sendRedirect("/");
				})
		        );
		
		
		http
		.sessionManagement(sess -> sess
				.maximumSessions(4)
				.maxSessionsPreventsLogin(false))
		.sessionManagement(sess -> sess
				.sessionFixation().newSession());
		
		return http.build();
		
	}
	
}
