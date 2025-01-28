package com.FitnessWebApp.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.FitnessWebApp.Entities.User;
import com.FitnessWebApp.Services.UserService;


@Controller
public class UserProfileController {
     @Autowired
	    private UserService userService;

	    // Displaying the user profile
	   @GetMapping("/profile")
	   public String profile(Model model, Principal principal) {
	       String userEmail = principal.getName();
	       try {
	           User user = userService.getUserByEmail(userEmail);
	           if (user != null) {
	               model.addAttribute("user", user);
	               return "profile";
	           } else {
	               return "redirect:/login";
	           }
	       } catch (Exception e) {
	           e.printStackTrace(); // Optional: Log the error
	           return "redirect:/login";
	       }
	   }
       @GetMapping("/profile/edit")
	   public String showEditProfileForm(Model model, Principal principal) {
	       if (principal == null) {
	           return "redirect:/login";
	       }

	       String userEmail = principal.getName();
	       User user = userService.getUserByEmail(userEmail);

	       if (user != null) {
	           // If the user profile is null, initialize it
	          
	           user.setFullName(userEmail);
	           userService.saveUser(user); // Save the user to persist the new UserProfile
	       }
	       model.addAttribute("user", user);
	       return "editProfile";
	   }
}
