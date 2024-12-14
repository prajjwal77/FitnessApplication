package com.FitnessApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FitnessApplication.Model.User;
import com.FitnessApplication.Model.Workout;
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long>{
	 List<Workout> findAllByUserId(Long userId);
	 
}
