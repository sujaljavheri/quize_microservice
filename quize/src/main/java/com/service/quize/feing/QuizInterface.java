package com.service.quize.feing;

import com.service.quize.Model.QuestionWrapper;
import com.service.quize.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="question-service")
public interface QuizInterface {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category,
                                                            @RequestParam Integer numberOfQuestion);

    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionId);

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
