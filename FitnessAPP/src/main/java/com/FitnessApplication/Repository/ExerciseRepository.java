package com.FitnessApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FitnessApplication.Model.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long>{

	List<Exercise> findByWorkoutId(Long workoutId);

}
