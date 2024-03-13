package com.Ecommerce.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.RedirectStrategy;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.Ecommerce.model.Role;
import com.Ecommerce.model.User;
import com.Ecommerce.repository.RoleRepository;
import com.Ecommerce.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Google0Auth2SuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	
	private RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		OAuth2AuthenticationToken token=(OAuth2AuthenticationToken)authentication;
		String email=token.getPrincipal().getAttribute("email");
		if (userRepository.findUserByEmail(email).isPresent()) {
			
		}else {
			User user= new User();
			user.setFirstName(token.getPrincipal().getAttribute("given_name"));
			user.setLastname(token.getPrincipal().getAttributes().get("family_name").toString());
			user.setEmail(email);
		    List<Role> roles=new ArrayList<>();
		    roles.add(roleRepository.findById(2).get());
		    user.setRoles(roles);
		    userRepository.save(user);
		    
		}
	//redirectStrategy.isRedirected(httpServletRequest , httpServletResponse,"/");
	}
	
}
