package com.FitnessApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FitnessApplication.Model.Meal;
import com.FitnessApplication.Model.User;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long>{
	 List<Meal> findAllByUserId(Long userId);
	    
	
}
