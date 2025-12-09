package com.question.service.controller;

import com.question.service.service.QuestionService;
import com.question.service.Model.QuestionWrapper;
import com.question.service.Model.Questions;
import com.question.service.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    Environment environment;

    @Autowired
    QuestionService questionService;
    @GetMapping("/allquestion")
    public List<Questions> questions(){

        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public List<Questions> getByCategory(@PathVariable String category){
        return questionService.getByCategory(category);
    }

    @PostMapping("/addquestion")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question) {
        return questionService.addQuestion(question);
    }

    @PostMapping("deletquestion/{Id}")
    public String deletquestion(@PathVariable Integer Id){
        return questionService.deletQuestion(Id);
    }

    //generate question
    // getquestion (questionid)
    //getScore

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, @RequestParam Integer numberOfQuestion) {
        return questionService.getQuestionForQuiz(category, numberOfQuestion);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(
            @RequestBody List<Integer> questionId) {
        return questionService.getQuestionFromId(questionId);
    }


    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }

}





