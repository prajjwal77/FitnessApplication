package com.FitnessApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FitnessApplication.Repository.DietRepository;
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
    private DietRepository dietRepository;

    @Autowired
    private MotivationalContentRepository motivationalContentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // User Authentication
    public boolean login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    // User Registration
    public boolean register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return false; // Email already exists
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    // Fetch Current User
    public User getCurrentUser() {
        // Logic to fetch the authenticated user
        // For simplicity, assuming a static user for now
        return userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Update User Profile
    @Transactional
    public void updateUserProfile(UserProfile userProfile) {
        User user = getCurrentUser();
        user.setUserProfile(userProfile);
        userRepository.save(user);
    }

    // Workout Management
    public List<Workout> getWorkouts() {
        User user = getCurrentUser();
        return workoutRepository.findByUser(user);
    }

    public void addWorkout(String workoutName, int duration) {
        User user = getCurrentUser();
        Workout workout = new Workout();
        workout.setName(workoutName);
        workout.setDuration(duration);
        workout.setUser(user);
        workoutRepository.save(workout);
    }

    public WorkoutStats getWorkoutStats() {
        User user = getCurrentUser();
        int completedWorkouts = workoutRepository.countByUser(user);
        int caloriesBurned = workoutRepository.sumCaloriesBurnedByUser(user);
        return new WorkoutStats(completedWorkouts, caloriesBurned);
    }

    // Diet Management
    public List<Meal> getDietPlans() {
        User user = getCurrentUser();
        return dietRepository.findByUser(user);
    }

    public void addMeal(String mealName, int calories) {
        User user = getCurrentUser();
        Meal meal = new Meal();
        meal.setName(mealName);
        meal.setCalories(calories);
        meal.setUser(user);
        dietRepository.save(meal);
    }

    public DietStats getDietStats() {
        User user = getCurrentUser();
        int caloriesConsumed = dietRepository.sumCaloriesByUser(user);
        int calorieGoal = user.getUserProfile().getCalorieGoal();
        return new DietStats(caloriesConsumed, calorieGoal);
    }

    // Motivational Content
    public String getMotivationalQuote() {
        return motivationalContentRepository.findRandomQuote();
    }

    public List<String> getMotivationalVideos() {
        return motivationalContentRepository.findAllVideoUrls();
    }
}
