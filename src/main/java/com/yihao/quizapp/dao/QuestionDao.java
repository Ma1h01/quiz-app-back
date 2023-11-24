package com.yihao.quizapp.dao;

import org.springframework.stereotype.Repository;

import com.yihao.quizapp.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


// Abstract out the seven steps to write and process a SQL query for any CRUD operation
@Repository // <table name, type of primary key>
public interface QuestionDao extends JpaRepository<Question, Integer>{

    // JPA is smart enough to find any table entry by any one column.
    // We don't need to write a entire new SQL query
    List<Question> findByCategory(String category);

    // This annotation allows you to write SQL query and customize the method.
    // the colon ':' allow you to pass the parameters of method to the SQL query
    @Query(value = "SELECT * FROM Question WHERE category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);

    @Query(value = "SELECT * FROM Question ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestions(int numQ);
}
