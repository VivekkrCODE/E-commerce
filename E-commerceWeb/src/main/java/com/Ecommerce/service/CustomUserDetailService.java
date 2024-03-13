package com.Ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Ecommerce.model.CoustomUserDetail;
import com.Ecommerce.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<com.Ecommerce.model.User> user=userRepository.findUserByEmail(email);
		user.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		return user.map(CoustomUserDetail::new).get();
	}

}
