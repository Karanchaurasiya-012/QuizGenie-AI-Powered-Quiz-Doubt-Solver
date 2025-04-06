package com.example.quiz.service;

import com.example.quiz.model.Question;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {

    public List<Question> extractQuestionsFromPdf(MultipartFile file) throws IOException {
        List<Question> questions = new ArrayList<>();

        try (InputStream is = file.getInputStream();
             PDDocument document = PDDocument.load(is)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);
            String[] lines = text.split("\n");

            for (int i = 0; i < lines.length; i += 6) {
                if (i + 5 < lines.length) {
                    Question question = new Question();
                    question.setQuestionText(lines[i].trim());
                    question.setOptionA(lines[i + 1].trim());
                    question.setOptionB(lines[i + 2].trim());
                    question.setOptionC(lines[i + 3].trim());
                    question.setOptionD(lines[i + 4].trim());
                    question.setCorrectAnswer(lines[i + 5].trim());
                    questions.add(question);
                }
            }
        }
        return questions;
    }
}
