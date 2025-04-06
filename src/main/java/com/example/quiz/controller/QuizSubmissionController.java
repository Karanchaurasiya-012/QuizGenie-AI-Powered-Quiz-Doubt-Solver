package com.example.quiz.controller;

import com.example.quiz.model.Question;
import com.example.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class QuizSubmissionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/submitQuiz")
    public String submitQuiz(@RequestParam Map<String, String> allParams, Model model) {
        int score = 0;
        List<Question> questions = questionService.getAllQuestions();

        for (Question question : questions) {
            String userAnswer = allParams.get("question_" + question.getId());
            if (userAnswer != null && userAnswer.equals(question.getCorrectAnswer())) {
                score++;
            }
        }

        model.addAttribute("score", score);
        return "result";
    }
}
