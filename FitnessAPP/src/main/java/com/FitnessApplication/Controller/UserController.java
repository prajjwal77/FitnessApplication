package com.FitnessApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homePage(Model model) {
        // Home page data, like user goals and motivation
        model.addAttribute("workoutStats", userService.getWorkoutStats());
        model.addAttribute("dietStats", userService.getDietStats());
        model.addAttribute("motivationalQuote", userService.getMotivationalQuote());
        return "home"; // Return the home.html Thymeleaf template
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Return login.html
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, RedirectAttributes attributes) {
        boolean success = userService.login(email, password);
        if (success) {
            return "redirect:/";
        } else {
            attributes.addFlashAttribute("error", "Invalid credentials!");
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // Return register.html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes attributes) {
        boolean success = userService.register(user);
        if (success) {
            attributes.addFlashAttribute("success", "Registration successful! Please login.");
            return "redirect:/login";
        } else {
            attributes.addFlashAttribute("error", "Registration failed. Email already exists!");
            return "redirect:/register";
        }
    }

    @GetMapping("/profile")
    public String userProfile(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "profile"; // Return profile.html
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute UserProfile userProfile, RedirectAttributes attributes) {
        userService.updateUserProfile(userProfile);
        attributes.addFlashAttribute("success", "Profile updated successfully!");
        return "redirect:/profile";
    }

    @GetMapping("/workout")
    public String workoutPage(Model model) {
        model.addAttribute("workouts", userService.getWorkouts());
        return "workout"; // Return workout.html
    }

    @PostMapping("/workout/add")
    public String addWorkout(@RequestParam String workoutName, @RequestParam int duration, RedirectAttributes attributes) {
        userService.addWorkout(workoutName, duration);
        attributes.addFlashAttribute("success", "Workout added successfully!");
        return "redirect:/workout";
    }

    @GetMapping("/diet")
    public String dietPage(Model model) {
        model.addAttribute("meals", userService.getDietPlans());
        return "diet"; // Return diet.html
    }

    @PostMapping("/diet/add")
    public String addMeal(@RequestParam String mealName, @RequestParam int calories, RedirectAttributes attributes) {
        userService.addMeal(mealName, calories);
        attributes.addFlashAttribute("success", "Meal added successfully!");
        return "redirect:/diet";
    }

    @GetMapping("/motivation")
    public String motivationalContent(Model model) {
        model.addAttribute("videos", userService.getMotivationalVideos());
        return "motivation"; // Return motivation.html
    }
}