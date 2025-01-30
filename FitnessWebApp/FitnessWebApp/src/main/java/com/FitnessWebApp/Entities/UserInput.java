package com.FitnessWebApp.Entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int height;
    private int weight;
    private int age;
    private String gender;
    private String fitnessLevel;
    private String goal;
    //private String prompt;
    @OneToOne(mappedBy = "userInput")
    private User user; // This maps UserInput back to User

}
