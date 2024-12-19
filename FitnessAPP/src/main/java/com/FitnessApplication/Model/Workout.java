package com.FitnessApplication.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Workout {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Workout name
    private int duration; // in minutes
    private String description; // Detailed description of the workout
    private String type; // e.g., "Strength", "Cardio", etc.
    private String difficulty; // "Beginner", "Intermediate", "Advanced"
    private String category; // "Yoga", "HIIT", "Pilates", etc.
    private String goal; // "Strength Training", "Bodybuilding", "Weight Loss", "Lean Gaining", etc.
    
    private String targetBodyPart; // E.g., "Chest", "Arms", "Legs", "Full Body"
    private String equipmentRequired; // E.g., "Dumbbells", "Barbell", "Resistance Bands", "None"
    private String videoUrl; // Link to the workout video
    private String chartUrl; // Link to workout charts (e.g., progress tracking, exercises breakdown)

    private int caloriesBurned; // Estimated calories burned per session
    private int sets; // Number of sets
    private int reps; // Number of repetitions per set
    private int restTime; // Rest time between sets (in seconds)

    @ElementCollection
    private List<String> tips; // Tips specific to the workout (e.g., "Keep your back straight")

    @ManyToOne
    private User user; // The user who created or follows the workout

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "workout")
    private List<Exercise> exercises; // List of exercises included in the workout

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getTargetBodyPart() {
		return targetBodyPart;
	}

	public void setTargetBodyPart(String targetBodyPart) {
		this.targetBodyPart = targetBodyPart;
	}

	public String getEquipmentRequired() {
		return equipmentRequired;
	}

	public void setEquipmentRequired(String equipmentRequired) {
		this.equipmentRequired = equipmentRequired;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getChartUrl() {
		return chartUrl;
	}

	public void setChartUrl(String chartUrl) {
		this.chartUrl = chartUrl;
	}

	public int getCaloriesBurned() {
		return caloriesBurned;
	}

	public void setCaloriesBurned(int caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public int getRestTime() {
		return restTime;
	}

	public void setRestTime(int restTime) {
		this.restTime = restTime;
	}

	public List<String> getTips() {
		return tips;
	}

	public void setTips(List<String> tips) {
		this.tips = tips;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}
    
    
}
