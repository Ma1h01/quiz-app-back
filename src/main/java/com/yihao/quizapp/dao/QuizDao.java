package com.yihao.quizapp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yihao.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{
    
    // Even Quiz obejct has an ManyToMany relationship with Question object, JPA is smart enough to find the respective table entry
    @Query(value = "SELECT * FROM Quiz ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Quiz> findARandomQuiz();
}
