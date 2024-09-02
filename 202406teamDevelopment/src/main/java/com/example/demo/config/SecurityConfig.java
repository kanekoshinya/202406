package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.service.CustomUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				//静的リソースへのアクセスを許可している
				.authorizeRequests().mvcMatchers("/css/**", "/js/**", "/images/**").permitAll()
				// ログインページへのリクエストに対しては、認証は不要。
				.mvcMatchers("/login/**").permitAll()
				// それ以外のリクエストは認証が必要
				.anyRequest().authenticated().and()
				// formログインであることを示す
				.formLogin().loginPage("/login")
				// ログイン成功後の遷移先を指定
				.successHandler(myAuthenticationSuccessHandler()).permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService)
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		return (request, response, authentication) -> {
			response.sendRedirect("/userList");
		};
	}
}
