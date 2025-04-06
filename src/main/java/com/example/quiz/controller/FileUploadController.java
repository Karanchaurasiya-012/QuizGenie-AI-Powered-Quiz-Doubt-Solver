package com.example.quiz.controller;

import com.example.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileUploadController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/uploadPdf")
    public String uploadPdf(@RequestParam("file") MultipartFile file, Model model) {
        try {
            questionService.saveQuestionsFromPdf(file);
            model.addAttribute("message", "PDF uploaded and processed successfully!");
        } catch (IOException e) {
            model.addAttribute("message", "Error processing PDF: " + e.getMessage());
        }
        return "upload";  // Stay on the same upload page and show the message
    }
}
