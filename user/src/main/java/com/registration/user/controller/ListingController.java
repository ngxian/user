package com.registration.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.registration.user.model.Users;
import com.registration.user.repository.UserRepository;
import com.registration.user.service.UserService;

import jakarta.websocket.server.PathParam;


@Controller
public class ListingController {

	@Autowired private final UserRepository userRepository;
	@Autowired private UserService userService;
	
    @Autowired
    public ListingController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/userListing")
    public String listUsers(Model model, String keyword) {
        List<Users> users = userRepository.findAll();
        
        if(keyword != null) {
        	if(keyword.equals(""))
        		users = userRepository.findAll();
        	else
        		users = userService.findByKeyword(keyword);
        }
        else {
        	users = userRepository.findAll();
        }
        
        model.addAttribute("users", users);
        
        return "userListing"; // View name (HTML template)
    }
    
    @GetMapping("/userListing/{field}")
    public String listUsersWithSort(Model model, 
    								@PathVariable("field") String field,
    								@PathParam("sortDir") String sortDir) {
        
    	List<Users> users;
        users = userService.findAllWithSort(field, sortDir);
        
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("users", users);
        
        return "userListing"; // View name (HTML template)
    }
}
