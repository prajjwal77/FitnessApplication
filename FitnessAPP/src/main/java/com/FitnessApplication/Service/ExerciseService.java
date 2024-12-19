package com.FitnessApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FitnessApplication.Model.Exercise;
import com.FitnessApplication.Repository.ExerciseRepository;

@Service
public class ExerciseService {

	@Autowired
	private ExerciseRepository exerciseRepository;

	public List<Exercise> getExercisesByWorkout(Long workoutId) {
		return exerciseRepository.findByWorkoutId(workoutId);
	}

	public Exercise createExercise(Exercise exercise) {
		return exerciseRepository.save(exercise);
	}

	public void deleteExercise(Long id) {
		exerciseRepository.deleteById(id);
	}
}
