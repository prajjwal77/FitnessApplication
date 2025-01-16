package com.FitnessWebApp.Services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.FitnessWebApp.Entities.UserInput;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class OpenAIService {
   
    public String generatePlanUsingGPT(UserInput userInput) {
        String plan = "Generated a custom workout plan for " + userInput.getFitnessLevel() +
                      " with a goal of " + userInput.getGoal() +
                      ".\n\n" +
                      generateWorkoutPlan(userInput) +
                      generateDietPlan(userInput);
        return plan;
    }

    private String generateWorkoutPlan(UserInput userInput) {
        String fitnessLevel = userInput.getFitnessLevel();
        String goal = userInput.getGoal();

        StringBuilder workoutPlan = new StringBuilder();
        workoutPlan.append("\nWorkout Plan:\n");

        // Generate workout plan based on goal and fitness level
        if (goal.equalsIgnoreCase("Muscle Gain")) {
            if (fitnessLevel.equalsIgnoreCase("Beginner")) {
                workoutPlan.append("Week 1:\n");
                workoutPlan.append("- Monday: Full-body workout (3 sets of 12 reps)\n");
                workoutPlan.append("- Wednesday: Full-body workout (3 sets of 12 reps)\n");
                workoutPlan.append("- Friday: Full-body workout (3 sets of 12 reps)\n");
                
                workoutPlan.append("\nWeek 2:\n");
                workoutPlan.append("- Monday: Full-body workout (3 sets of 12 reps)\n");
                workoutPlan.append("- Wednesday: Full-body workout (3 sets of 12 reps)\n");
                workoutPlan.append("- Friday: Full-body workout (3 sets of 12 reps)\n");
            } else if (fitnessLevel.equalsIgnoreCase("Intermediate")) {
                workoutPlan.append("Week 1:\n");
                workoutPlan.append("- Monday: Upper body workout (4 sets of 10 reps)\n");
                workoutPlan.append("- Wednesday: Lower body workout (4 sets of 10 reps)\n");
                workoutPlan.append("- Friday: Full-body workout (4 sets of 10 reps)\n");
                
                workoutPlan.append("\nWeek 2:\n");
                workoutPlan.append("- Monday: Upper body workout (4 sets of 10 reps)\n");
                workoutPlan.append("- Wednesday: Lower body workout (4 sets of 10 reps)\n");
                workoutPlan.append("- Friday: Full-body workout (4 sets of 10 reps)\n");
            } else if (fitnessLevel.equalsIgnoreCase("Advanced")) {
                workoutPlan.append("Week 1:\n");
                workoutPlan.append("- Monday: Chest and Triceps (5 sets of 8 reps)\n");
                workoutPlan.append("- Tuesday: Back and Biceps (5 sets of 8 reps)\n");
                workoutPlan.append("- Thursday: Legs and Shoulders (5 sets of 8 reps)\n");
                workoutPlan.append("- Friday: Full-body workout (5 sets of 8 reps)\n");
                
                workoutPlan.append("\nWeek 2:\n");
                workoutPlan.append("- Monday: Chest and Triceps (5 sets of 8 reps)\n");
                workoutPlan.append("- Tuesday: Back and Biceps (5 sets of 8 reps)\n");
                workoutPlan.append("- Thursday: Legs and Shoulders (5 sets of 8 reps)\n");
                workoutPlan.append("- Friday: Full-body workout (5 sets of 8 reps)\n");
            }
        } else if (goal.equalsIgnoreCase("Weight Loss")) {
            if (fitnessLevel.equalsIgnoreCase("Beginner")) {
                workoutPlan.append("Week 1:\n");
                workoutPlan.append("- Monday: Cardio (30 min)\n");
                workoutPlan.append("- Wednesday: Cardio (30 min)\n");
                workoutPlan.append("- Friday: Cardio (30 min)\n");
                
                workoutPlan.append("\nWeek 2:\n");
                workoutPlan.append("- Monday: Cardio (35 min)\n");
                workoutPlan.append("- Wednesday: Cardio (35 min)\n");
                workoutPlan.append("- Friday: Cardio (35 min)\n");
            } else if (fitnessLevel.equalsIgnoreCase("Intermediate")) {
                workoutPlan.append("Week 1:\n");
                workoutPlan.append("- Monday: HIIT (30 min)\n");
                workoutPlan.append("- Wednesday: Cardio (40 min)\n");
                workoutPlan.append("- Friday: HIIT (30 min)\n");
                
                workoutPlan.append("\nWeek 2:\n");
                workoutPlan.append("- Monday: HIIT (35 min)\n");
                workoutPlan.append("- Wednesday: Cardio (45 min)\n");
                workoutPlan.append("- Friday: HIIT (35 min)\n");
            } else if (fitnessLevel.equalsIgnoreCase("Advanced")) {
                workoutPlan.append("Week 1:\n");
                workoutPlan.append("- Monday: HIIT (45 min)\n");
                workoutPlan.append("- Tuesday: Cardio (45 min)\n");
                workoutPlan.append("- Thursday: HIIT (45 min)\n");
                workoutPlan.append("- Friday: Cardio (45 min)\n");
                
                workoutPlan.append("\nWeek 2:\n");
                workoutPlan.append("- Monday: HIIT (50 min)\n");
                workoutPlan.append("- Tuesday: Cardio (50 min)\n");
                workoutPlan.append("- Thursday: HIIT (50 min)\n");
                workoutPlan.append("- Friday: Cardio (50 min)\n");
            }
        } else if (goal.equalsIgnoreCase("Maintenance")) {
            if (fitnessLevel.equalsIgnoreCase("Beginner")) {
                workoutPlan.append("Week 1:\n");
                workoutPlan.append("- Monday: Full-body workout (3 sets of 10 reps)\n");
                workoutPlan.append("- Wednesday: Full-body workout (3 sets of 10 reps)\n");
                workoutPlan.append("- Friday: Full-body workout (3 sets of 10 reps)\n");
                
                workoutPlan.append("\nWeek 2:\n");
                workoutPlan.append("- Monday: Full-body workout (3 sets of 10 reps)\n");
                workoutPlan.append("- Wednesday: Full-body workout (3 sets of 10 reps)\n");
                workoutPlan.append("- Friday: Full-body workout (3 sets of 10 reps)\n");
            } else if (fitnessLevel.equalsIgnoreCase("Intermediate")) {
                workoutPlan.append("Week 1:\n");
                workoutPlan.append("- Monday: Upper body workout (4 sets of 8 reps)\n");
                workoutPlan.append("- Wednesday: Lower body workout (4 sets of 8 reps)\n");
                workoutPlan.append("- Friday: Full-body workout (4 sets of 8 reps)\n");
                
                workoutPlan.append("\nWeek 2:\n");
                workoutPlan.append("- Monday: Upper body workout (4 sets of 8 reps)\n");
                workoutPlan.append("- Wednesday: Lower body workout (4 sets of 8 reps)\n");
                workoutPlan.append("- Friday: Full-body workout (4 sets of 8 reps)\n");
            } else if (fitnessLevel.equalsIgnoreCase("Advanced")) {
                workoutPlan.append("Week 1:\n");
                workoutPlan.append("- Monday: Chest and Triceps (5 sets of 6 reps)\n");
                workoutPlan.append("- Tuesday: Back and Biceps (5 sets of 6 reps)\n");
                workoutPlan.append("- Thursday: Legs and Shoulders (5 sets of 6 reps)\n");
                workoutPlan.append("- Friday: Full-body workout (5 sets of 6 reps)\n");
                
                workoutPlan.append("\nWeek 2:\n");
                workoutPlan.append("- Monday: Chest and Triceps (5 sets of 6 reps)\n");
                workoutPlan.append("- Tuesday: Back and Biceps (5 sets of 6 reps)\n");
                workoutPlan.append("- Thursday: Legs and Shoulders (5 sets of 6 reps)\n");
                workoutPlan.append("- Friday: Full-body workout (5 sets of 6 reps)\n");
            }
        }

        return workoutPlan.toString();
    }

    private String generateDietPlan(UserInput userInput) {
        String fitnessLevel = userInput.getFitnessLevel();
        String goal = userInput.getGoal();

        StringBuilder dietPlan = new StringBuilder();
        dietPlan.append("\nDiet Plan:\n");

        // Generate diet plan based on goal and fitness level
        if (goal.equalsIgnoreCase("Muscle Gain")) {
            if (fitnessLevel.equalsIgnoreCase("Beginner")) {
                dietPlan.append("- Breakfast: Oatmeal with protein shake\n");
                dietPlan.append("- Lunch: Chicken breast with rice and veggies\n");
                dietPlan.append("- Dinner: Fish with vegetables\n");

                dietPlan.append("\nWeek 2:\n");
                dietPlan.append("- Breakfast: Scrambled eggs with toast\n");
                dietPlan.append("- Lunch: Grilled chicken with quinoa and veggies\n");
                dietPlan.append("- Dinner: Salmon with broccoli\n");
            } else if (fitnessLevel.equalsIgnoreCase("Intermediate")) {
                dietPlan.append("- Breakfast: Scrambled eggs with toast\n");
                dietPlan.append("- Lunch: Grilled chicken with quinoa and veggies\n");
                dietPlan.append("- Dinner: Salmon with broccoli\n");

                dietPlan.append("\nWeek 2:\n");
                dietPlan.append("- Breakfast: Omelette with spinach\n");
                dietPlan.append("- Lunch: Beef steak with rice and salad\n");
                dietPlan.append("- Dinner: Chicken with sweet potato\n");
            } else if (fitnessLevel.equalsIgnoreCase("Advanced")) {
                dietPlan.append("- Breakfast: Omelette with spinach\n");
                dietPlan.append("- Lunch: Beef steak with rice and salad\n");
                dietPlan.append("- Dinner: Chicken with sweet potato\n");

                dietPlan.append("\nWeek 2:\n");
                dietPlan.append("- Breakfast: Protein smoothie\n");
                dietPlan.append("- Lunch: Grilled chicken with vegetables\n");
                dietPlan.append("- Dinner: Salmon with vegetables\n");
            }
        } else if (goal.equalsIgnoreCase("Weight Loss")) {
            // Add similar structure for Weight Loss and Maintenance as above
        } else if (goal.equalsIgnoreCase("Maintenance")) {
            // Add similar structure for Maintenance as above
        }

        return dietPlan.toString();
    }

    // public void generatePDF(String plan, String filePath) {
    //     Document document = new Document();
    //     try {
    //         PdfWriter.getInstance(document, new FileOutputStream(filePath));
    //         document.open();
    //         document.add(new Paragraph(plan));
    //     } catch (DocumentException | FileNotFoundException e) {
    //         e.printStackTrace();
    //     } finally {
    //         document.close();
    //     }
    // }
    

//    private static final int MAX_RETRIES = 3;

//     @Value("${openai.api.key}")  // Ensure to set the OpenAI API key in application.properties
//     private String apiKey;

//     private final RestTemplate restTemplate;

//     public OpenAIService(RestTemplate restTemplate) {
//         this.restTemplate = restTemplate;
//     }

//     public String generatePlanUsingGPT(UserInput userInput) {
//         String apiUrl = "https://api.openai.com/v1/chat/completions";
//         Map<String, Object> requestBody = createRequestBody(userInput);
//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON);
//         headers.setBearerAuth(apiKey);
//         HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

//         for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
//             try {
//                 // Make the API call
//                 ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, request, Map.class);
//                 return extractPlanFromResponse(response);
//             } catch (HttpClientErrorException.TooManyRequests e) {
//                 if (attempt < MAX_RETRIES - 1) {
//                     try {
//                         // Sleep for 5 seconds before retrying
//                         Thread.sleep(5000);
//                     } catch (InterruptedException ie) {
//                         Thread.currentThread().interrupt();
//                     }
//                 } else {
//                     throw new RuntimeException("Exceeded maximum retries due to API rate limiting", e);
//                 }
//             } catch (Exception e) {
//                 throw new RuntimeException("Error while calling OpenAI API", e);
//             }
//         }
//         return null; // After retries
//     }

//     private Map<String, Object> createRequestBody(UserInput userInput) {
//         // Prepare the request body for OpenAI API
//         return Map.of(
//                 "model", "gpt-3.5-turbo", // Example model
//                 "messages", List.of(Map.of("role", "user", "content", userInput.getPrompt()))
//         );
//     }

//     private String extractPlanFromResponse(ResponseEntity<Map> response) {
//         // Extract the plan from the API response
//         Map<String, Object> responseBody = response.getBody();
//         // Logic to extract and return the plan text from the response
//         return (String) responseBody.get("choices"); // Adjust depending on response structure
//     }

//     private String createPrompt(UserInput userInput) {
//         return String.format("Create a detailed weekly workout and diet plan for the following details:\n" +
//                 "- Age: %d\n" +
//                 "- Gender: %s\n" +
//                 "- Height: %d cm\n" +
//                 "- Weight: %d kg\n" +
//                 "- Fitness Level: %s\n" +
//                 "- Goal: %s\n\n" +
//                 "Provide a 7-day workout plan with daily exercises and a corresponding diet plan for each day.",
//                 userInput.getAge(), userInput.getGender(), userInput.getHeight(),
//                 userInput.getWeight(), userInput.getFitnessLevel(), userInput.getGoal());
//     }
}
