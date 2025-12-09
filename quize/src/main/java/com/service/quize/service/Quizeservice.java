package com.service.quize.service;

import com.service.quize.Dao.QuizeDao;
import com.service.quize.Model.*;
import com.service.quize.feing.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Quizeservice {
    @Autowired
    QuizeDao quizedao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int num, String title) {

        List<Integer> questions = quizInterface.getQuestionForQuiz(category, num).getBody();

        Quize quize = new Quize();
        quize.setTitle(title);
        quize.setQuestion(questions);
        quizedao.save(quize);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }
    public ResponseEntity<List<QuestionWrapper>> getQuizeQuestions(Integer id) {
        Quize quiz = quizedao.findById(id).get();
        List<Integer> quizIds = quiz.getQuestions();

        ResponseEntity<List<QuestionWrapper>> question = quizInterface.getQuestionFromId(quizIds);

        return question;
    }

    public ResponseEntity<Integer> submitResponse(Integer id, List<Response> response) {
        ResponseEntity<Integer> res = quizInterface.getScore(response);
        return res;
    }

    public ResponseEntity<List<Quize>> getAllQuizzes() {
        List<Quize> quizzes = quizedao.findAll();
        return ResponseEntity.ok(quizzes);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizByTitle(String title) {
        Quize quiz = quizedao.findByTitle(title);
        if (quiz == null) {
            return ResponseEntity.notFound().build();
        }
        List<Integer> questionIds = quiz.getQuestions();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionFromId(questionIds);
        return questions;
    }

}
