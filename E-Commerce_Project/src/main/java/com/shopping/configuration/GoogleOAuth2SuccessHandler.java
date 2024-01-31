package com.shopping.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.shopping.model.Role;
import com.shopping.model.User;
import com.shopping.repository.RoleRepository;
import com.shopping.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {
			OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
			String email = token.getPrincipal().getAttributes().get("email").toString();
			if(userRepository.findUserByEmail(email).isPresent())
			{
				
			}else
			{
				User user = new User();
				user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
				user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
				user.setEmail(email);
				List<Role> roles = new ArrayList<>();
				roles.add(roleRepository.findById(2).get());
				user.setRoles(roles);
				userRepository.save(user);
			}
			
			redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse, "/");
	}
	

}
