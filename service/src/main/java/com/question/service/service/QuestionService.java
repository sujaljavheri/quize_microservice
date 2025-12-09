
package com.question.service.service;

import com.question.service.Dao.QuestionDao;
import com.question.service.Model.QuestionWrapper;
import com.question.service.Model.Questions;
import com.question.service.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
//    public List<Question> getAllQuestions() {
//        return questionDao.findAll();
//    }
    public List<Questions> getAllQuestions() {
        List<Questions> list = questionDao.findAll();
        return list;
    }

    public List<Questions> getByCategory(String category) {
        return questionDao.findByCategoryIgnoreCase(category);
    }

    public ResponseEntity<String> addQuestion(Questions question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("success" ,HttpStatus.CREATED) ;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<> ("error: " + e.getMessage() ,HttpStatus.BAD_REQUEST );
        }
    }

    public String deletQuestion(Integer Id) {
        try{
            questionDao.deleteById(Id);
            return "success";
        }catch (Exception e){
            return "error " + e.getMessage();
        }
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numberOfQuestion) {

        List<Integer> questions = questionDao.findRnadomQuestionByCategory(category, numberOfQuestion);
        return new ResponseEntity<>(questions , HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionId) {
//        List<Questions> questions = new ArrayList<>();
//        List<QuestionWrapper> wrappers = new ArrayList<>();
//
//        for(Integer q : questionId){
//            questions.add(questionDao.findById(q).get());
//        }
//
//        for(Questions question : questions){
//            QuestionWrapper wrapper = new QuestionWrapper();
//            wrapper.setId(question.getId());
//            wrapper.setQuestionTitle(question.getQuestionTitle());
//            wrapper.setOption1(question.getOption1());
//            wrapper.setOption2(question.getOption2());
//            wrapper.setOption3(question.getOption3());
//            wrapper.setOption4(question.getOption4());
//
//            wrappers.add(wrapper);
//        }
//
//        return new ResponseEntity<>(wrappers, HttpStatus.OK);

        List<QuestionWrapper> wrappers = new ArrayList<>();

        for(Integer id : questionId){

            Questions question = questionDao.findById(id).orElse(null);

            if(question == null) continue;

            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());

            wrappers.add(wrapper);
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right =0;
        for(Response res : responses){
            Questions question = questionDao.findById(res.getId()).get();
            if(res.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
