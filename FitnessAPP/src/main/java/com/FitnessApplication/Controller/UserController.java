package com.FitnessApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.FitnessApplication.Model.User;
import com.FitnessApplication.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String Home() {
        return "home";
    }
  //showing register page
  	@GetMapping("/register")
      public String showRegistrationForm(Model model) {
          model.addAttribute("user", new User());
          return "register";
      }
  	//...........................................................................................................
  	//registering new user
  	 @PostMapping("/register")
  	    public String registerUser(@ModelAttribute("user") User user) {
  	     userService.saveUser(user);  
  		 return "register";
  	    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "logout";
    }
}