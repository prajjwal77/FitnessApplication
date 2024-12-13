package com.FitnessApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FitnessApplication.Model.Meal;
import com.FitnessApplication.Model.User;

@Repository
public interface DietRepository extends JpaRepository<Meal, Long>{
	 List<Meal> findByUser(User user);
	    int sumCaloriesByUser(User user); // Assume you calculate total calories in the database
	
}
