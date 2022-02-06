package com.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.model.UserPrinicipal;
import com.bank.repository.UserDetailsRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDetailsRepository jwtUserDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		com.bank.entity.UserDetailsEntity user = jwtUserDetailsRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("UserDetailsEntity not found with username: " + userName);
		}
		return new UserPrinicipal(user);
	}

}