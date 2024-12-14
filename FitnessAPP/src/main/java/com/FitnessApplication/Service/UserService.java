package com.FitnessApplication.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.FitnessApplication.Model.Meal;
import com.FitnessApplication.Model.MotivationalContent;
import com.FitnessApplication.Model.User;
import com.FitnessApplication.Model.UserProfile;
import com.FitnessApplication.Model.Workout;
import com.FitnessApplication.Repository.MealRepository;
import com.FitnessApplication.Repository.MotivationalContentRepository;
import com.FitnessApplication.Repository.UserRepository;
import com.FitnessApplication.Repository.WorkoutRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private MotivationalContentRepository motivationalContentRepository;

    @Autowired
    private MealRepository mealRepository;

    // Create a new user
    public User createUser(User user) {
        if (user.getRole() == null) {
            user.setRole("USER"); // Default role
        }
        user.setEnabled(true); // Default enabled
        return userRepository.save(user);
    }

    // Update an existing user
    public User updateUser(Long userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setProfile(updatedUser.getProfile());
        existingUser.setRole(updatedUser.getRole());
        existingUser.setEnabled(updatedUser.isEnabled());
        return userRepository.save(existingUser);
    }

    // Delete a user
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // Get user by ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Add a workout to a user
    public Workout addWorkoutToUser(Long userId, Workout workout) {
        User user = getUserById(userId);
        workout.setUser(user);
        return workoutRepository.save(workout);
    }

    // Get all workouts for a user
    public List<Workout> getWorkoutsByUserId(Long userId) {
        return workoutRepository.findAllByUserId(userId);
    }

    // Add motivational content
    
//    
//    public List<String> getAllVideoUrls() {
//        List<String> videoUrls = motivationalContentRepository.findAllVideoUrls();
//        return videoUrls != null ? videoUrls : new ArrayList<>();
//    }

    // Get all motivational content
    public List<MotivationalContent> getAllMotivationalContent() {
        return motivationalContentRepository.findAll();
    }
    // Add motivational content (global, not tied to a user)
    public MotivationalContent addMotivationalContent(MotivationalContent content) {
        return motivationalContentRepository.save(content);
    }

    // Add a meal to a user
    public Meal addMealToUser(Long userId, Meal meal) {
        User user = getUserById(userId);
        meal.setUser(user);
        return mealRepository.save(meal);
    }

    // Get all meals for a user
    public List<Meal> getMealsByUserId(Long userId) {
        return mealRepository.findAllByUserId(userId);
    }
}
