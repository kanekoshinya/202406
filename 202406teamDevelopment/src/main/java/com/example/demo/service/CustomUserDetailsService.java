package com.example.demo.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if("yuta".equals(username)) {
			return new CustomUserDetails("yuta", "password",Collections.emptyList() );
		}
		throw new UsernameNotFoundException(
				"Given username is not found "
				);
	}
}
