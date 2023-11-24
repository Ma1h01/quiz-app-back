package com.yihao.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yihao.quizapp.model.Question;
import com.yihao.quizapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
    
    // Autowired instantiates the object for us
    @Autowired
    QuestionService questionService;

    // ResponseEntity allows you to return the status code as well
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return questionService.getAllQuestions();
    }

    // the "{}" is optional and allows the client to pass in parameter in the url
    // the @PathVariable specifies which url path parameter is assigned to the parameter in the method
    // if both parameters have the same name, dont need to specify the path parameter name in the annotation
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("category") String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    // the @RequestBody assembles a JSON format data into a Question object so that we can add it to the table
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    // @PutMapping modifies an enry of the table
    @PutMapping("remove/{id}")
    public ResponseEntity<String> removeQuestion(@PathVariable int id) {
        return questionService.removeQuestion(id);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }
}
