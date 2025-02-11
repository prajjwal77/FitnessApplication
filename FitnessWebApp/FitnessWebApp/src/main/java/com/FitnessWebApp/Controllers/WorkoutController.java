package com.FitnessWebApp.Controllers;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;

import com.FitnessWebApp.Entities.ContactUs;
import com.FitnessWebApp.Entities.User;
import com.FitnessWebApp.Entities.UserInput;
import com.FitnessWebApp.Services.ContactUsService;
import com.FitnessWebApp.Services.OpenAIService;
import com.FitnessWebApp.Services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class WorkoutController {
    @Autowired
    private OpenAIService openAIService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactUsService contactUsService;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("userInput", new UserInput());
        return "home";
    }

    // showing register page
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // registering new user
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please log in.");
        return "redirect:/login"; // Redirect to the login page
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

    @GetMapping("/about")
    public String about() {
        return "about";
    }

   
    @GetMapping("/generate")
    public String showGenerateForm(Model model) {
        model.addAttribute("userInput", new UserInput());
        return "generate";
    }
    @PostMapping("/generate")
    public String generatePlan(@ModelAttribute UserInput userInput, Model model) {
        // Generate workout plan using GPT
        String generatedPlan = openAIService.generatePlanUsingGPT(userInput);
    
        // // Define the file path for saving the PDF
        // String pdfFilePath = "generated_plan.pdf";
    
        // // Generate the PDF with the workout and diet plan
        // openAIService.generatePDF(generatedPlan, pdfFilePath);
    
        model.addAttribute("fitnessPlan", generatedPlan);
        return "fitnessPlan"; // View name to display the generated plan
    }

    // @GetMapping("/downloadPlan")
    // public ResponseEntity<Resource> downloadPlan() {
    //     // Set the file path for the generated PDF
    //     Path path = Paths.get("generated_plan.pdf");
    //     Resource resource = null;

    //     try {
    //         resource = new UrlResource(path.toUri());
    //     } catch (MalformedURLException e) {
    //         e.printStackTrace();
    //     }

    //     return ResponseEntity.ok()
    //             .contentType(MediaType.APPLICATION_PDF)
    //             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generated_plan.pdf")
    //             .body(resource);
    // }
    @GetMapping("/programs")
    public String programs() {
        return "programs";
    }

    @PostMapping("/contact")
    public String quickContact(@RequestParam String email, @RequestParam String message,
            RedirectAttributes redirectAttributes) {
        ContactUs contactMessage = new ContactUs();
        contactMessage.setEmail(email);
        contactMessage.setMessage(message);
        contactUsService.saveContactMessage(contactMessage);
        redirectAttributes.addFlashAttribute("message", "Your message has been sent successfully.");
        return "redirect:/";
    }
}
