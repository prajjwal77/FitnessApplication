package com.FitnessApplication.Security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.FitnessApplication.Model.User;
import com.FitnessApplication.Repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{

	  @Autowired
	    private UserRepository userRepository;

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        User user = userRepository.findByEmail(email);
	        return new org.springframework.security.core.userdetails.User(
	                user.getEmail(),
	                user.getPassword(),
	                user.isEnabled(),
	                true, true, true,
	                AuthorityUtils.createAuthorityList("ROLE_USER"));
	    }
}
