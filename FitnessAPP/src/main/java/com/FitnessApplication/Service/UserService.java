package com.FitnessApplication.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.FitnessApplication.Model.User;
import com.FitnessApplication.Repository.UserRepository;


@Service
public class UserService {
	 @Autowired
	    private UserRepository userRepository;

	 @Autowired
	    private BCryptPasswordEncoder passwordEncoder;

	    public User getUserByEmail(String email) {
	        User user = userRepository.findByEmail(email);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found with email: " + email);
	        }
	        return user;
	    }

	    public User getUserById(Long userId) {
	        return userRepository.findById(userId).orElse(null);
	    }

	    public void saveUser(User user) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        userRepository.save(user);
	    }
}
