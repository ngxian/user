package com.registration.user.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.registration.user.model.Users;
import com.registration.user.repository.UserRepository;

@Service
public class UserService {
	
	UserRepository userRepository;
	
	PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registerUser(Users users) {
    	// Check if the email already exists
    	Users existingUser = userRepository.findByEmail(users.getEmail());
        if (existingUser != null) 
        	throw new RuntimeException("Records already exists");
        
        // Implement user registration logic (e.g., encryption of the password)
    	String encodedPassword = this.passwordEncoder.encode(users.getPassword());
    	users.setPassword(encodedPassword);
    	
    	userRepository.save(users);
    }
    
    //get user by keyword
    public List<Users> findByKeyword(String keyword) {
    	return userRepository.findByKeyword(keyword);
    }
    
    public List<Users> findAllWithSort(String field, String direction) {
    	Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(field).ascending() :
    	     Sort.by(field).descending();
    	
    	return userRepository.findAll(sort);
    }
}