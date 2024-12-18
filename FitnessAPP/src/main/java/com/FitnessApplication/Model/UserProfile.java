package com.FitnessApplication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;

@Entity
public class UserProfile {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String age;
	    private String height;
	    private String weight;
	    private String fitnessGoal;

	    @OneToOne(mappedBy = "profile")
	    private User user;

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getAge() {
	        return age;
	    }

	    public void setAge(String age) {
	        this.age = age;
	    }

	    public String getHeight() {
	        return height;
	    }

	    public void setHeight(String height) {
	        this.height = height;
	    }

	    public String getWeight() {
	        return weight;
	    }

	    public void setWeight(String weight) {
	        this.weight = weight;
	    }

	    public String getFitnessGoal() {
	        return fitnessGoal;
	    }

	    public void setFitnessGoal(String fitnessGoal) {
	        this.fitnessGoal = fitnessGoal;
	    }

	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }
}
