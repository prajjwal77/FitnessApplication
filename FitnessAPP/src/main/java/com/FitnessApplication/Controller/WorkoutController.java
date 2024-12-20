package com.FitnessApplication.Controller;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.FitnessApplication.Model.Exercise;
import com.FitnessApplication.Model.User;
import com.FitnessApplication.Model.Workout;
import com.FitnessApplication.Service.ExerciseService;
import com.FitnessApplication.Service.UserService;
import com.FitnessApplication.Service.WorkoutService;



@Controller
@RequestMapping("/workouts")
public class WorkoutController {

	@Autowired
	private WorkoutService workoutService;

	@Autowired
	private ExerciseService exerciseService;

	@Autowired
	private UserService userService;

	// View all workouts with optional filters
	@GetMapping
	public String viewAllWorkouts(@RequestParam(required = false) String category,
			@RequestParam(required = false) String type, @RequestParam(required = false) String difficulty,
			Model model) {
		List<Workout> workouts;
		if (category != null || type != null || difficulty != null) {
			workouts = workoutService.filterWorkouts(category, type, difficulty);
		} else {
			workouts = workoutService.getAllWorkouts();
		}
		model.addAttribute("workouts", workouts);
		return "workout/list";
	}

	// View workout details
	@GetMapping("/{id}")
	public String viewWorkoutDetails(@PathVariable Long id, Model model) {
		Workout workout = workoutService.getWorkoutById(id);
		if (workout == null) {
			return "error/404"; // Handle not found
		}
		model.addAttribute("workout", workout);
		model.addAttribute("exercises", exerciseService.getExercisesByWorkout(id));
		return "workout/details";
	}

	// Create workout form
	@GetMapping("/create")
	public String createWorkoutForm(Model model) {
		model.addAttribute("workout", new Workout());
		return "workout/create";
	}

	

	// Edit workout form
	@GetMapping("/edit/{id}")
	public String editWorkoutForm(@PathVariable Long id, Model model) {
		Workout workout = workoutService.getWorkoutById(id);
		if (workout == null) {
			return "error/404";
		}
		model.addAttribute("workout", workout);
		return "workout/edit";
	}

	// Update workout
	@PostMapping("/edit/{id}")
	public String updateWorkout(@PathVariable Long id, @ModelAttribute Workout workout) {
		workoutService.updateWorkout(id, workout);
		return "redirect:/workouts";
	}

	// Delete workout
	@GetMapping("/delete/{id}")
	public String deleteWorkout(@PathVariable Long id) {
		workoutService.deleteWorkout(id);
		return "redirect:/workouts";
	}

	// Add exercise to workout form
	@GetMapping("/{id}/exercises/add")
	public String addExerciseForm(@PathVariable Long id, Model model) {
		model.addAttribute("exercise", new Exercise());
		model.addAttribute("workoutId", id);
		return "exercise/create";
	}

	// Save new exercise
	@PostMapping("/{id}/exercises/add")
	public String addExercise(@PathVariable Long id, @ModelAttribute Exercise exercise) {
		Workout workout = workoutService.getWorkoutById(id);
		if (workout != null) {
			exercise.setWorkout(workout);
			exerciseService.createExercise(exercise);
		}
		return "redirect:/workouts/" + id;
	}

	// Delete exercise
	@GetMapping("/exercises/delete/{id}")
	public String deleteExercise(@PathVariable Long id) {
		exerciseService.deleteExercise(id);
		return "redirect:/workouts";
	}

}