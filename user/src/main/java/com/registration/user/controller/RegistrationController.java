package com.registration.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.registration.user.model.Users;
import com.registration.user.service.UserService;

@Controller
public class RegistrationController {

	@Autowired private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/userRegistration")
    public String listUsers(Model model) {
        return "userRegistration"; // View name (HTML template)
    }

    @PostMapping("/register")
    public String registerUser(Users users, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "userRegistration"; // Return to the registration form with validation errors
        }

        userService.registerUser(users); // Call your service to save the user to the database

        return "userRegistrationSuccess"; // Redirect to a success page
    }
    
   
}
