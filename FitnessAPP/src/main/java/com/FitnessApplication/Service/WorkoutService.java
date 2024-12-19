package com.FitnessApplication.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FitnessApplication.Model.Workout;
import com.FitnessApplication.Repository.WorkoutRepository;

@Service
public class WorkoutService {
	@Autowired
	private WorkoutRepository workoutRepository;

	public List<Workout> getAllWorkouts() {
		return workoutRepository.findAll();
	}

	public Workout getWorkoutById(Long id) {
		return workoutRepository.findById(id).orElse(null);
	}

	public Workout createWorkout(Workout workout) {
		return workoutRepository.save(workout);
	}

	public Workout updateWorkout(Long id, Workout updatedWorkout) {
		Optional<Workout> existingWorkout = workoutRepository.findById(id);
		if (existingWorkout.isPresent()) {
			Workout workout = existingWorkout.get();
			workout.setName(updatedWorkout.getName());
			workout.setDescription(updatedWorkout.getDescription());
			workout.setDuration(updatedWorkout.getDuration());
			workout.setType(updatedWorkout.getType());
			workout.setDifficulty(updatedWorkout.getDifficulty());
			workout.setCategory(updatedWorkout.getCategory());
			workout.setGoal(updatedWorkout.getGoal());
			workout.setTargetBodyPart(updatedWorkout.getTargetBodyPart());
			workout.setEquipmentRequired(updatedWorkout.getEquipmentRequired());
			workout.setVideoUrl(updatedWorkout.getVideoUrl());
			workout.setChartUrl(updatedWorkout.getChartUrl());
			workout.setCaloriesBurned(updatedWorkout.getCaloriesBurned());
			workout.setSets(updatedWorkout.getSets());
			workout.setReps(updatedWorkout.getReps());
			workout.setRestTime(updatedWorkout.getRestTime());
			workout.setTips(updatedWorkout.getTips());
			return workoutRepository.save(workout);
		}
		return null;
	}

	public void deleteWorkout(Long id) {
		workoutRepository.deleteById(id);
	}
}
