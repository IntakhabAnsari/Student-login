package com.rahi.in.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.rahi.in.entity.UserInfo;
import com.rahi.in.exception.UserNotFoundException;
import com.rahi.in.repository.UserInfoRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
 
	@Autowired
	private UserInfoRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = repository.findByEmail(username);
		
		return userInfo.map(UserInfoUserDetails::new)
				.orElseThrow(()->new UserNotFoundException("User not found "+username));
	}

}