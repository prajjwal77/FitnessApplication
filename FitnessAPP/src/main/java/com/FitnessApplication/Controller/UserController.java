package com.FitnessApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.FitnessApplication.Model.Meal;
import com.FitnessApplication.Model.MotivationalContent;
import com.FitnessApplication.Model.User;
import com.FitnessApplication.Model.UserProfile;
import com.FitnessApplication.Model.Workout;
import com.FitnessApplication.Service.UserService;

@Controller
public class UserController {


    @Autowired
    private UserService userService;
    
    // Home page
    @GetMapping("/")
    public String homePage(Model model) {
        // Add any required data to the model for the home page
        model.addAttribute("welcomeMessage", "Welcome to FitnessApp!");
        // You can also add user data or other content needed for the home page
        return "home"; // This should match the name of your Thymeleaf template
    }


    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Update a user
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }

    // Delete a user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    // Get a user by ID
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    // Add a workout
    @PostMapping("/{userId}/workouts")
    public Workout addWorkoutToUser(@PathVariable Long userId, @RequestBody Workout workout) {
        return userService.addWorkoutToUser(userId, workout);
    }

    // Get workouts
    @GetMapping("/{userId}/workouts")
    public List<Workout> getWorkoutsByUserId(@PathVariable Long userId) {
        return userService.getWorkoutsByUserId(userId);
    }

    // Add a meal
    @PostMapping("/{userId}/meals")
    public Meal addMealToUser(@PathVariable Long userId, @RequestBody Meal meal) {
        return userService.addMealToUser(userId, meal);
    }

    // Get meals
    @GetMapping("/{userId}/meals")
    public List<Meal> getMealsByUserId(@PathVariable Long userId) {
        return userService.getMealsByUserId(userId);
    }
    // Add motivational content (global, not tied to a user)
    @PostMapping("/motivational-content")
    public MotivationalContent addMotivationalContent(@RequestBody MotivationalContent content) {
        return userService.addMotivationalContent(content);
    }

    // Get all motivational content
    @GetMapping("/motivational-content")
    public List<MotivationalContent> getAllMotivationalContent() {
        return userService.getAllMotivationalContent();
    }
}