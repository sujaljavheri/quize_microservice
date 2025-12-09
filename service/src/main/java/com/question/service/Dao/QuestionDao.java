package com.question.service.Dao;

import com.question.service.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer> {

    List<Questions> findByCategoryIgnoreCase(String category);

    @Query(value = "SELECT q.Id FROM questions q WHERE q.category =:category ORDER BY RANDOM() LIMIT :num", nativeQuery
            = true)
    List<Integer> findRnadomQuestionByCategory(String category, int num);
}
