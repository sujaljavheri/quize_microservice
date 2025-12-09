package com.service.quize.controller;

import com.service.quize.Model.*;
import com.service.quize.service.Quizeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
     Quizeservice quizeservice;
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizedto){
//        return new ResponseEntity<String> ("i am here", HttpStatus.OK);
        return quizeservice.createQuiz(quizedto.getCategoryName(), quizedto.getNumQuestions(), quizedto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizeservice.getQuizeQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitResponse(@PathVariable Integer id, @RequestBody List<Response> response){
        return quizeservice.submitResponse(id,response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quize>> getAllQuizzes() {
        return quizeservice.getAllQuizzes();
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<QuestionWrapper>> getQuizByTitle(@PathVariable String title) {
        return quizeservice.getQuizByTitle(title);
    }

}
