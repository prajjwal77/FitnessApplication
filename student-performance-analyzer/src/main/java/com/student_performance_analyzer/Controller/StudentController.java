package com.student_performance_analyzer.Controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StudentController {

@GetMapping("/")
public String uploadPage() {
     return "upload";
}

@PostMapping("/upload")
public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
    try {
        // Create the uploads directory if it doesn't exist
        String uploadDir = System.getProperty("java.io.tmpdir") + "/uploads";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // create the directory
        }

        // Save the file to that directory
        String filePath = uploadDir + "/" + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        model.addAttribute("message", "File uploaded successfully.");
        return "upload_success"; // your success view

    } catch (IOException e) {
        e.printStackTrace();
        model.addAttribute("error", "File upload failed.");
        return "upload_error"; // your error view
    }
}
}