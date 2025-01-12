package com.FitnessWebApp.Services;

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

@Service
public class OpenAIService {

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
