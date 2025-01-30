package com.FitnessWebApp.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.FitnessWebApp.Entities.User;
import com.FitnessWebApp.Services.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    // @Autowired
    // private UserService userService;

    // @GetMapping
    // public String viewProfile(Model model, Principal principal) {
    //     String email = principal.getName(); // Fetch logged-in user's email
    //     User user = userService.findByEmail(email);
    //     model.addAttribute("user", user);
    //     return "profile"; // Thymeleaf template name
    // }

    // @PostMapping("/updatePhoto")
    // public String updatePhoto(@RequestParam("profilePhoto") MultipartFile file, Principal principal) {
    //     String email = principal.getName();
    //     userService.updateProfilePhoto(email, file);
    //     return "redirect:/profile";
    // }
}
