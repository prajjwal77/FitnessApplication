package com.FitnessWebApp.Entities;

import lombok.Data;

@Data
public class UserInput {
    private String aiSource;
    private int height;
    private int weight;
    private int age;
    private String gender;
    private String fitnessLevel;
    private String goal;
    private String prompt;

}
